package com.show.mapper;

import com.show.pojo.StorageRecord;
import com.show.vo.StorageRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StorageRecordMapper {


    List<StorageRecordVo> pages(
            @Param("currentPage") Integer currentPage
            ,@Param("pageSize") Integer pageSize
            ,@Param("queryGoodsName") String queryGoodsName
            ,@Param("queryPayStatus") String queryPayStatus
            ,@Param("querySupplierId") String querySupplierId
    );

    Integer count(@Param("queryGoodsName") String queryGoodsName, @Param("queryPayStatus") String queryPayStatus,@Param("querySupplierId") String querySupplierId);


    Integer delete(String id);

    StorageRecordVo queryById(String id);

    Integer update(StorageRecord storageRecord);

    Integer add(StorageRecord storageRecord);
}
