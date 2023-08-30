package com.show.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    /**
     * create table cvs_db.t_sys_user
     * (
     *     id            bigint auto_increment comment '主键ID'
     *         primary key,
     *     account       varchar(15) null comment '账号',
     *     realName      varchar(15) null comment '真是姓名',
     *     password      varchar(15) null comment '密码',
     *     sex           int         null comment '性别（1:女、 2:男）',
     *     birthday      date        null comment '出生日期（年-月-日）',
     *     phone         varchar(15) null comment '手机号码',
     *     address       varchar(30) null comment '用户地址',
     *     roleId        bigint      null comment '用户角色id',
     *     createdUserId bigint      null comment '创建人',
     *     createdTime   datetime    null comment '创建时间',
     *     updatedUserId bigint      null comment '修改人',
     *     updatedTime   datetime    null comment '修改时间'
     * )
     *     comment '系统用户' collate = utf8_unicode_ci;
     */
    private String id;
    private String account;
    private String realName;
    private String password;
    private Integer sex;
    private String birthday;
    private String phone;
    private String address;
    private String roleId;
    private String createdUserId;
    private String createdTime;
    private Integer updatedUserId;
    private String updatedTime;
}
