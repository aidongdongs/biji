package cn.itcast.hotel.service.impl;

import cn.itcast.hotel.mapper.HotelMapper;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {


    @Autowired
    private RestHighLevelClient client;

    @Override
    public PageResult search(RequestParams params) {

        PageResult pageResult = null;
        // 关键字搜索
        String key = params.getKey();
//        if (key==null || key.equals("")){
//            searchRequest.source().query(QueryBuilders.matchAllQuery());
//        }else {
//            searchRequest.source().query(QueryBuilders.matchQuery("all",key));
//        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 添加关键字条件
        if (key != null && !"".equals(key)) {

            boolQueryBuilder.must(QueryBuilders.matchQuery("all",key));
        }
        // 城市条件
        if (params.getCity()!=null && !params.getCity().equals("")){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery("city",params.getCity())
            );
        }
        // 品牌条件
        if (params.getBrand()!=null && !"".equals(params.getBrand())){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery("brand",params.getBrand())
            );
        // 星级条件
        if (params.getStarName()!= null && !params.getStarName().equals("")){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery("starName",params.getStarName())
            );
        }
        }
        // 价格
        if (params.getMaxPrice()!=null && params.getMaxPrice()!=null){
            boolQueryBuilder.filter(
                    QueryBuilders.rangeQuery("price").gte(params.getMinPrice()).lte(params.getMaxPrice())
            );

        }

        SearchRequest request = new SearchRequest("hotel");

        // 排序
        if (params.getSortBy()!=null && !params.getSortBy().equals("")){
            request.source().sort(params.getSortBy());
        }

        request.source().query(boolQueryBuilder);

        // 分页
        request.source().from((params.getPage()-1)*params.getSize()).size(params.getSize());


        // 发送请求
        try {
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
         pageResult  = handlerResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pageResult;
    }

    private  PageResult handlerResponse(SearchResponse response){
        // 集合
        List<HotelDoc> list = new ArrayList<>();

        SearchHits hits = response.getHits();
        //获取总条数
        long total = hits.getTotalHits().value;

        SearchHit[] hits1 = hits.getHits();
        Arrays.stream(hits1).forEach(item->{
            String stringJson = item.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(stringJson, HotelDoc.class);
            list.add(hotelDoc);
        });

      return  new PageResult(total,list);
    }
}
