package com.show.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.rmi.server.UID;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StorageRecord {
    /**
     * create table cvs_db.t_storage_record
     * (
     *     id            bigint auto_increment comment '主键ID'
     *         primary key,
     *     srCode        varchar(20)    null comment '入库记录编码',
     *     goodsName     varchar(20)    null comment '商品名称',
     *     goodsDesc     varchar(50)    null comment '商品描述',
     *     goodsUnit     varchar(10)    null comment '商品单位',
     *     goodsCount    decimal(20, 2) null comment '入库数量',
     *     totalAmount   decimal(20, 2) null comment '入库商品总额',
     *     payStatus     int            null comment '支付状态（1：未支付 2：已支付）',
     *     createdUserId bigint         null comment '创建人id',
     *     createdTime   datetime       null comment '创建时间',
     *     updatedUserId bigint         null comment '修改人id',
     *     updatedTime   datetime       null comment '修改时间',
     *     supplierId    bigint         null comment '供货商ID'
     * )
     *     comment '入库记录' collate = utf8_unicode_ci;
     */
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
    private String supplierId;
}
