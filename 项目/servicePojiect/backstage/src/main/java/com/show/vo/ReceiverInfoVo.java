package com.show.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前端传递过来的修改loseGoods表的数据
 */
@Data
@Accessors(chain = true)
public class ReceiverInfoVo {
    /**
     * receiptor:"",
     * phone:"",
     * responsiblePerson:"",
     * WayOfClaim:"",
     * remark:""
     * id:""
     */
    private String receiptor;
    private String phone;
    private String responsiblePerson;
    private String wayOfClaim;
    private String remark;
    private String id;


}