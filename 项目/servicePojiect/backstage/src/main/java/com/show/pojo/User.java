package com.show.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * create table user(
     *     id varchar(255) primary key comment '主键唯一id',
     *     name varchar(255) not null comment '用户名',
     *     password varchar(255) default '123456' comment '密码默认123456',
     *     phone varchar(255) comment '手机号码',
     *     role int not null comment '角色 0 为管理员工 1为员工',
     *     isDelete int not null comment '是否删除了这条数据 0 表示被删除了 1表示没有被删除 ',
     *     stole varchar(255) not null comment '盐值',
     *     creator varchar(255) not null comment '创建人',
     *     creatorTime datetime not null  comment '创建时间',
     *     changeCreator varchar(255) not null comment '修改时间',
     *     changeTime datetime not null  comment '修改时间',
     *     forbidden int    default 0 comment '禁用 用户注册的会被禁用，需要管理员工开启 0禁用 1启用'
     * ) charset =utf8;
     */
    private String id;
    private String name;
    private String password;
    private String phone;
    private int role;
    private int isDelete;
    private String stole;
    private String creator;
    private String creatorTime;
    private String changeCreator;
    private String changeTime;
    private int forbidden;


}
