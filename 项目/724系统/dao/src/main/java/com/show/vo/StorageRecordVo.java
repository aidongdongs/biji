package com.show.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageRecordVo {

    private Integer id;
    private String srCode;
    private String goodsName;
    private String goodsDesc;
    private String goodsUnit;
    private String goodsCount;
    private Integer payStatus;
    private Integer createdUserId;
    private Date createdTime;
    private Integer updatedUserId;
    private Date updatedTime;
    private Integer comment;
    private String totalAmount;
    private String supName;
    private String supplierId;
}
