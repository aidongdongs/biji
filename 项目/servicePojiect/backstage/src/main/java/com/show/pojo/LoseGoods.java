package com.show.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

//失物表

@TableName("LoseGoods")
@Data
@Accessors(chain = true)
public class LoseGoods implements Serializable {
    /**
     * create table loseGoods(
     *     id varchar(255) primary key ,
     *     description varchar(255)  not null comment '物品的描述',
     *     img varchar(255) comment '照片存放名字',
     *     storage varchar(255) not null  comment '存放位置',
     *     FoundPeople varchar(255) comment '拾取人',
     *     departmentId int not null comment '拾取人部门',
     *     equipmentId int not null comment '拾取物品位置',
     *     classify varchar(255) not null  comment '物品分类',
     *     classifyId varchar(255) not null comment '分类编号',
     *     recipient varchar(255) not null  comment '接收人，客服',
     *     isDelete int not null  comment '是否删除0删除 1没有删除',
     *     creator	varchar(255) not null  comment '记录创建人',
     *     creatorTime datetime not null  comment '创建时间',
     *     changeCreator	varchar(255) not null  comment '修改人',
     *     changeTime		datetime not null  comment '修改时间',
     *     receiptor varchar(255) comment '认领人',
     *     dateOfClaim datetime comment '认领日期',
     *     phone varchar(255) comment '联系电话',
     *     responsiblePerson varchar(255) comment '经办人',
     *     WayOfClaim int comment '认领方式' comment '0自提 1邮寄',
     *     remark varchar(300) comment '备注'
     *     )charset =utf8;
     */
    private String id;
    private String description;
    private String img;

    private String foundPeople;
    private Integer departmentId;
    private Integer equipmentId;
    private String classify;
    private String classifyId;
    private String recipient;
    private Integer isDelete;
    private String creator;
    private String creatorTime;
    private String changeCreator;
    private String changeTime;
    private String receiptor;
    private String dateOfClaim;
    private String phone;
    private String responsiblePerson;
    private Integer WayOfClaim;
    private String remark;


}
