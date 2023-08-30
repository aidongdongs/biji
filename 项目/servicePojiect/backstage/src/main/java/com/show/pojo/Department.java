package com.show.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 部门表
 */
@Data
@Accessors(chain = true)
public class Department {
    /**
     * create table department(
     *     id int primary key auto_increment comment '主键',
     *     name varchar(255) comment '部门名称',
     *     weight int not null comment '权重'
     * )charset =utf8;
     */
    private String id;
    private String name;
    private int weight;


}
