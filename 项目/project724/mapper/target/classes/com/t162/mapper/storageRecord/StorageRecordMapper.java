package com.t162.mapper.storageRecord;

import com.t162.domain.StorageRecord;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StorageRecordMapper {
   List<StorageRecord> selectAllStorageRecord(StorageRecord storageRecord);

   int addStorageRecord(StorageRecord storageRecord);
   @Delete("delete from t_storage_record where id=#{id};")
   int delById(Integer id);

   StorageRecord selectById(Integer id);

   int updateById(StorageRecord storageRecord);


}
