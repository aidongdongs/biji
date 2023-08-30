package com.show.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Supplier {
    /**
     * create table cvs_db.t_supplier
     * (
     *     id            bigint auto_increment comment '主键ID'
     *         primary key,
     *     supCode       varchar(20) null comment '供货商编号',
     *     supName       varchar(20) null comment '供货商名称',
     *     supDesc       varchar(50) null comment '供货商描述',
     *     supContact    varchar(20) null comment '供货商联系人',
     *     supPhone      varchar(20) null comment '联系电话',
     *     supAddress    varchar(50) null comment '供货商地址',
     *     supFax        varchar(20) null comment '传真',
     *     createdUserId bigint      null comment '创建人id',
     *     createdTime   datetime    null comment '创建时间',
     *     updatedTime   datetime    null comment '修改时间',
     *     updatedUserId bigint      null comment '修改人id'
     * )
     *     comment '药品供货商' collate = utf8_unicode_ci;
     */
    private Long  id;
    private String supCode;
    private String supName;
    private String supDesc;
    private String supContact;
    private String supPhone;
    private String supAddress;
    private String supFax;
    private Long createdUserId;
    private Date createdTime;
    private Date updatedTime;
    private Long updatedUserId;
}
