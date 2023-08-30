package cn.itcast.hotel;

import cn.itcast.hotel.constants.HotelConstants;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.lucene.queryparser.xml.QueryBuilder;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

@SpringBootTest
public class HotelIndexTest {

    private RestHighLevelClient client;

    @Autowired
    private IHotelService iHotelService;

    @BeforeEach
    public void setUp(){
        this.client =new RestHighLevelClient(RestClient.builder(
               HttpHost.create("http://192.168.101.129:9200")
        ));
    }


    @Test
    public void testInit(){
        System.out.println(client);
    }


    // 创建索引库
    @Test
    public void createHotelIndex() throws IOException {
        // 创建 请求的对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");

        // 创建请求 参数
        request.source(HotelConstants.MAPPING_TEMPLATE, XContentType.JSON);

        // 发起 请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    // 删除索引库
    @Test
    public void deleteHotelIndex() throws IOException {

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("hotel");
        client.indices().delete(deleteIndexRequest,RequestOptions.DEFAULT);
    }

    // 查询索引库是否存在
    @Test
    public void existsHotelIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("hotel");

        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }



    // 新增文档数据
    @Test
    public void createIndexDocument() throws IOException {
        //查询数据库
        Hotel hotel = iHotelService.getById("61083");

        // 对象转换
        HotelDoc hotelDoc = new HotelDoc(hotel);
        String jsonString = JSON.toJSONString(hotelDoc);
        System.out.println(jsonString);

//        // 创建 请求对象
        IndexRequest indexRequest = new IndexRequest("hotel").id(hotelDoc.getId().toString());
//
        // 加载请求数据
        indexRequest.source(jsonString,XContentType.JSON);
//
//        // 发起请求
        client.index(indexRequest,RequestOptions.DEFAULT);
    }

    // 根据id查询文档数据
    @Test
    public void documentById() throws IOException {
        // 创建请求对象
        GetRequest getRequest = new GetRequest("hotel","36934");
        // 发送请求
        GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
        // 获取的数据转换为 String 类型
        String sourceAsString = documentFields.getSourceAsString();
        // String 转换为对象那
        HotelDoc hotelDoc = JSON.parseObject(sourceAsString, HotelDoc.class);
        System.out.println(hotelDoc);
    }

    // 根据id修改文档数据
    @Test
    public void updateDocument() throws IOException {
        //创建更新对象
        UpdateRequest updateRequest = new UpdateRequest("hotel","61083");
        // 更新数据
        updateRequest.doc(
                "city","上海2"
        );
        // 发送请求
        client.update(updateRequest,RequestOptions.DEFAULT);
    }

    // 根据id删除文档数据
    @Test
    public void deleteDocument() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("hotel","61083");

        client.delete(deleteRequest,RequestOptions.DEFAULT);
    }


    // 批量加载数据据到 es 搜索
    @Test
    public void loadDocument() throws IOException {
        // 数据库查询数据
        List<Hotel> list = iHotelService.list();
        // 提交桶
        BulkRequest bulkRequest = new BulkRequest();

        // 遍历数据集合
        list.forEach(item->{
            // 创建 增加数据对象
            IndexRequest indexRequest = new IndexRequest("hotel").id(item.getId().toString());
            // 数据转换
            HotelDoc hotelDoc = new HotelDoc(item);
            String jsonString = JSON.toJSONString(hotelDoc);
            // 将json 数据 ，加载到 增加数据对象中
            indexRequest.source(jsonString,XContentType.JSON);
            // 将增加数据对象加载到 提交桶中
            bulkRequest.add(indexRequest);
        });
        // 发起请求，将数据桶提交上去
        client.bulk(bulkRequest,RequestOptions.DEFAULT);
        }


        // 查询全部文档数据 并转换称为 java集合对象
    @Test
        public void match_all () throws IOException {

        List<HotelDoc> hotelDocs = new ArrayList<>();

        // 创建请求对象
        SearchRequest request = new SearchRequest("hotel");
        // 添加查询条件
        request.source()
                .query(QueryBuilders.matchAllQuery()).from(0).size(201);
        // 发起请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        System.out.println(search);


        // 解析请求
        SearchHits hits = search.getHits();
        // 获取请求数据总条数
        TotalHits totalHits = hits.getTotalHits();
        // 获取数据数组
        SearchHit[] hitsS = hits.getHits();
        // 打印数组，默认是10条
        Arrays.stream(hitsS).forEach(item -> {
//            System.out.println(item.toString());
            // 将数据转化为java对象
            HotelDoc hotelDoc = JSON.parseObject(item.getSourceAsString(), HotelDoc.class);
            hotelDocs.add(hotelDoc);
        });

        // 打印对象
        for (HotelDoc hotelDoc : hotelDocs) {
            System.out.println(hotelDoc);
        }
    }

    /**
     *
     * 根据 关键字进行查询 文档数据
     */
    @Test
    public void multi_match() throws IOException {
        SearchRequest searchRequest = new SearchRequest("hotel");
        searchRequest.source().query(
                QueryBuilders.matchQuery("all","如家")
        );

        SearchResponse search = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        Arrays.stream(hits1).forEach(item->{
            System.out.println(item.getSourceAsString());
        });
    }



    // 精确查询
    @Test
    public void trem() throws IOException {

        SearchRequest request = new SearchRequest("hotel");
        request.source().query(
                QueryBuilders.termQuery("brand","如家"));
        SearchResponse search = client.search(request,RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields);
        }
    }

    //  范围查询
    @Test
    public void range() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(
                QueryBuilders.rangeQuery("price").gte(100).lte(500)
        ).sort("price", SortOrder.ASC);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields);
        }
    }

    // 多家条件布尔查询
    @Test
    public void booleanQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("brand","如家"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(200));
        request.source().query(boolQueryBuilder);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields);
        }
    }

    // 分页查询
    @Test
    public void sort() throws IOException {
        SearchRequest searchRequest =  new SearchRequest("hotel");
        searchRequest.source().query(
                QueryBuilders.matchAllQuery()
        ).sort("price",SortOrder.ASC);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields);
        }

    }

    // 排序
    @Test
    public void page() throws IOException {
        SearchRequest request = new SearchRequest();
        request.source().query(
                QueryBuilders.matchAllQuery()
        ).from(1).size(99);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields);
        }
    }

    // 聚合
    @Test
    public void backet() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source()
                .size(0)
                .aggregation(
                AggregationBuilders
                        .terms("brand_agg")
                        .field("brand")
                        .size(20)

        );

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
//        System.out.println(search);
        Aggregations aggregations = search.getAggregations();
        Terms brandAgg = aggregations.get("brand_agg");
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            System.out.print(bucket.getKeyAsString()+"\t");
            System.out.print(bucket.getDocCount()+"\t");
            System.out.println();

        }

    }

    // 多条件查询
    @Test
    public void querys() throws IOException {
        SearchRequest request = new  SearchRequest("hotel");
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(20)
        );

        request.source().aggregation(AggregationBuilders
                .terms("cityAgg")
                .field("city")
                .size(100)
        );

        request.source().aggregation(AggregationBuilders
                .terms("starNameAgg")
                .field("starName")
                .size(100));

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = search.getAggregations();
        Map<String, List<String>> query = query(aggregations);
        List<String> brand = query.get("品牌");
        for (String s : brand) {
            System.out.println(s);
        }

        List<String> starName = query.get("星级");
        System.out.println("---星级---");
        for (String s : starName) {
            System.out.println(s);
        }

        List<String> city = query.get("城市");
        System.out.println("---城市---");
        for (String s : city) {
            System.out.println(s);
        }


    }


    public Map<String,List<String>> query(Aggregations aggregations){

        List<String> cityList = new ArrayList<>();
        List<String> brangList = new ArrayList<>();
        List<String> starNameList = new ArrayList<>();
        Map<String,List<String>> map = new HashMap();


        Terms cityAgg = aggregations.get("cityAgg");
        List<? extends Terms.Bucket> buckets = cityAgg.getBuckets();

        for (Terms.Bucket bucket : buckets) {
            cityList.add(bucket.getKeyAsString());
        }



        Terms brandAgg = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> brandAgg1 = brandAgg.getBuckets();

        for (Terms.Bucket bucket : brandAgg1) {
            brangList.add(bucket.getKeyAsString());
        }


        Terms starNameAgg = aggregations.get("starNameAgg");
        List<? extends Terms.Bucket> brandAgg2 = starNameAgg.getBuckets();

        for (Terms.Bucket bucket : brandAgg2) {
            starNameList.add(bucket.getKeyAsString());
        }

        map.put("品牌",brangList);
        map.put("城市",cityList);
        map.put("星级",starNameList);
        return map;
    }


    @AfterEach
    public void close() throws IOException {
        client.close();
    }
}


