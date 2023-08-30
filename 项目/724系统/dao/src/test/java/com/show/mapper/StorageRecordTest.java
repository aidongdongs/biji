package com.show.mapper;

import com.show.config.SpringConfig;
import com.show.pojo.StorageRecord;
import com.show.vo.StorageRecordVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class StorageRecordTest {

    @Autowired
   private   StorageRecordMapper mapper;

    @Test
    public void testPages(){


        List<StorageRecordVo> pages = mapper.pages(0, 3, null, null,null);
        for (int i = 0; i < pages.size(); i++) {
            System.out.println(pages.get(i));
        }
    }

    @Test
    public void testCount(){
        System.out.println(mapper.count(null, null,null));
    }

    @Test
    public void testDelete(){
        System.out.println(mapper.delete("1"));
    }

    @Test
    public void testQueryById(){
        System.out.println(mapper.queryById("2"));
    }


//    @Test
//    public void testUpdate(){
//        StorageRecord storageRecord = new StorageRecord();
//        storageRecord.setId(2);
//        storageRecord.setGoodsName("白色可乐");
//        Integer update = mapper.update(storageRecord);
//        System.out.println(update);
//    }

    @Test
    public void testAdd(){
        StorageRecord storageRecord = new StorageRecord();
        storageRecord.setSrCode("123").setGoodsName("12312").setGoodsUnit("123").setGoodsCount("123123").setTotalAmount("12312")
                .setPayStatus(2);
        System.out.println(mapper.add(storageRecord));
    }
}
