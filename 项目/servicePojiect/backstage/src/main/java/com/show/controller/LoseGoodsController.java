package com.show.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.show.ex.ParameterNullException;
import com.show.mapper.LoseGoodsMapper;;
import com.show.pojo.LoseGoods;
import com.show.service.LoseGoodsService;
import com.show.util.PageUtils;
import com.show.util.ValidationObjectUtil;
import com.show.vo.ItemInformationVo;
import com.show.vo.LoseGoodsVo;
import com.show.vo.ReceiverInfoVo;
import com.show.vo.RegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拾取物品接口类
 */
@RestController
@RequestMapping("/loseGoods")
@Slf4j
public class LoseGoodsController {

    @Autowired
    private LoseGoodsService service;

    @Autowired
    private LoseGoodsMapper mapper;

    /**
     * 增加拾取物品信息
     * @param itemInformationVo 传递过来的拾取物品的信息的Vo类对象
     * @param request  需要从request中去token的值
     * @return
     */
    @PostMapping("/add")
    public ResultJSON add(@RequestBody ItemInformationVo itemInformationVo, HttpServletRequest request){
        System.out.println(itemInformationVo.toString());
//        验证前端传递过来的参数 有一个为空就会抛出异常
        ValidationObjectUtil.ValidationObjectIsNull(itemInformationVo);
//        service层操作
        Integer add = service.add(itemInformationVo, request);
        return new ResultJSON("数据增加成功",ResultJSON.INSERT_DATA_OK,add);
    }

    /**
     * 分页查询
     * @param numberOfPages 分页页数
     * @param showNumber 每页显示数
     * @param phone 条件查询手机号码
     * @param isGet 是否领取了
     * @param recipient 接受人名字
     * @return
     */
    @PostMapping("/paging")
        public ResultJSON paging(Integer numberOfPages, Integer showNumber,String phone,String isGet,String recipient){
        log.info("进入分页查询");
        if (numberOfPages==null||showNumber==null){
            log.error("分页参数异常");
            throw  new ParameterNullException("分页参数有问题",ResultJSON.PARAMETER_ERR,null);
        }
        Map map = new HashMap();
        map.put("numberOfPages",numberOfPages);
        map.put("showNumber",showNumber);
        map.put("phone",phone);
        map.put("isGet",isGet);
        map.put("recipient",recipient);
        log.info("分页参数{}---{}",numberOfPages,showNumber);
        PageUtils pageUtils = service.loseGoods(map);
        return new  ResultJSON("查询成功",11,pageUtils);
    }

    /**
     * 删除拾取人信息
     * @param id 拾取物品信息的id
     * @param img 图片名字。需要删除
     * @return json对象
     */
    @DeleteMapping("/delete")
   public ResultJSON delete(String id,String img){
        if (id == null || img == null){
            throw new ParameterNullException("参数为空",ResultJSON.PARAMETER_ERR,null);
        }
        //将参数传递给服务层
        Integer delete = service.delete(id, img);
        return new ResultJSON("删除成功",ResultJSON.DELETE_DARA_OK,delete);
   }

    /**
     * 增加认领人信息
     * @param receiverInfoVo 前端传递过来的认领人的信息
     * @return  json数据
     */
   @PutMapping("/update")
   public ResultJSON update(@RequestBody ReceiverInfoVo receiverInfoVo){
       ValidationObjectUtil.ValidationObjectIsNull(receiverInfoVo);
        log.info("前端传递到过来的对象信息"+receiverInfoVo.toString());
       Integer integer = service.updateById(receiverInfoVo);
       return new  ResultJSON("增加成功",ResultJSON.UPDATE_OK,integer);
   }
}
