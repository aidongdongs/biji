package com.t162.service.storageRecord_service;

import com.t162.domain.StorageRecord;

import java.util.List;

public interface StorageRecordService {
    List<StorageRecord> selectAllStorageRecord(StorageRecord storageRecord);

    int addStorageRecord(StorageRecord storageRecord);

    int delById(Integer id);

    StorageRecord selectById(Integer id);

    int updateById(StorageRecord storageRecord);

}
