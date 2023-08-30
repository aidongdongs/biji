package com.show.service;

import com.show.pojo.StorageRecord;
import com.show.utill.PageUtils;
import com.show.vo.StorageRecordVo;

import java.util.List;

public interface IStorageRecordService {

    PageUtils pages(Integer currentPage, Integer pageSize, String queryGoodsName, String queryPayStatus,String querySupplierId);

    Integer delete (String id);

    StorageRecordVo queryById(String id);


    Integer update(StorageRecord storageRecord ,String uid);

    Integer add(StorageRecord storageRecord,String uid);
}
