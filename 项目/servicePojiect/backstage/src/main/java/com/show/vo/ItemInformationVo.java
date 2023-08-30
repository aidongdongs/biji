package com.show.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前端传递过来的获取拾物物品信息
 */

@Data
@Accessors(chain = true)
public class ItemInformationVo {
    /**
     *                  description: '',
     *                 FoundPeople: '',
     *                 departmentId: '',
     *                 equipmentId: '',
     *                 classify: "",
     *                 classifyId: "",
     *                 recipient: '',
     *                 img: ''
     */
    private String description;
    private String foundPeople;
    private Integer departmentId;
    private Integer equipmentId;
    private String classify;
    private String  classifyId;
    private String recipient;
    private String img;

}
