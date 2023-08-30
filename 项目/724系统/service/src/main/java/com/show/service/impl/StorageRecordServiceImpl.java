package com.show.service.impl;

import com.show.mapper.StorageRecordMapper;
import com.show.pojo.StorageRecord;
import com.show.pojo.Supplier;
import com.show.service.IStorageRecordService;
import com.show.utill.GetDateUtil;
import com.show.utill.PageUtils;
import com.show.vo.StorageRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class StorageRecordServiceImpl implements IStorageRecordService {

    @Autowired
    private StorageRecordMapper mapper;

    public  PageUtils pages(Integer currentPage, Integer pageSize, String queryGoodsName, String queryPayStatus,String querySupplierId) {
        //处理分页数字
        if (currentPage==1){
            currentPage=0;
        }
        if (currentPage>1){
            currentPage=currentPage-1;
        }
        currentPage = currentPage*pageSize;

        System.out.println(currentPage+"-"+pageSize+"-"+"-"+queryPayStatus+"-"+queryGoodsName);
        Integer count = mapper.count(queryGoodsName,queryPayStatus,querySupplierId);
        PageUtils page= new PageUtils(currentPage,pageSize,Long.valueOf(count));
        List<StorageRecordVo> pages = mapper.pages(currentPage, pageSize,queryGoodsName,queryPayStatus,querySupplierId);
        page.setList(pages);
        return page;
    }

    public Integer delete(String id) {
        return mapper.delete(id);
    }

    public StorageRecordVo queryById(String id) {
        return mapper.queryById(id);
    }

    public Integer update(StorageRecord storageRecord ,String uid) {
        return mapper.update(storageRecord);
    }

    public Integer add(StorageRecord storageRecord,String uid) {
        storageRecord.setCreatedUserId(Integer.valueOf(uid));
        storageRecord.setUpdatedUserId(Integer.valueOf(uid));
        return mapper.add(storageRecord);
    }
}
