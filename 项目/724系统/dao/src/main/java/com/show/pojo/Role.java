package com.show.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Role {
    /**
     * create table cvs_db.t_sys_role
     * (
     *     id            bigint auto_increment comment '主键ID'
     *         primary key,
     *     code          varchar(15) null comment '角色编码',
     *     roleName      varchar(15) null comment '角色名称',
     *     createdUserId bigint      null comment '创建者',
     *     createdTime   datetime    null comment '创建时间',
     *     updatedUserId bigint      null comment '修改者',
     *     updatedTime   datetime    null comment '修改时间'
     * )
     *     comment '系统角色' collate = utf8_unicode_ci;
     */
    private Integer id;
    private String code;
    private String roleName;
    private String createdUserId;
    private Date createdTime;
    private Integer updatedUserId;
    private Date updatedTime;
}
