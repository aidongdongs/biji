package com.show.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设备表
 */
@Data
@Accessors(chain = true)
public class Equipment {
    /**
     * #设备表
     * create table equipment(
     *     id int primary key  auto_increment comment '主键',
     *     name varchar(50) not null comment '设备名称',
     *     weight int not null comment '权重'
     * )charset =utf8;
     */
    private String id;
    private String name;
    private int weight;



}
