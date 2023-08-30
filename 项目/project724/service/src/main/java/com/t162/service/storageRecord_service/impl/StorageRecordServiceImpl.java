package com.t162.service.storageRecord_service.impl;

import com.t162.domain.StorageRecord;
import com.t162.mapper.storageRecord.StorageRecordMapper;
import com.t162.service.storageRecord_service.StorageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageRecordServiceImpl implements StorageRecordService {
    @Autowired
    private StorageRecordMapper storageRecordMapper;
    @Override
    public List<StorageRecord> selectAllStorageRecord(StorageRecord storageRecord) {
        return storageRecordMapper.selectAllStorageRecord(storageRecord);
    }

    @Override
    public int addStorageRecord(StorageRecord storageRecord) {
        return storageRecordMapper.addStorageRecord(storageRecord);
    }

    @Override
    public int delById(Integer id) {
        return storageRecordMapper.delById(id);
    }

    @Override
    public StorageRecord selectById(Integer id) {
        return storageRecordMapper.selectById(id);
    }

    @Override
    public int updateById(StorageRecord storageRecord) {
        return storageRecordMapper.updateById(storageRecord);
    }
}
