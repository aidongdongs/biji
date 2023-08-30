package com.show.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.show.controller.ResultJSON;
import com.show.ex.DeleteException;
import com.show.ex.InsertDateException;
import com.show.ex.UpdateException;
import com.show.mapper.DepartmentMapper;
import com.show.mapper.EquipmentMapper;
import com.show.mapper.LoseGoodsMapper;
import com.show.pojo.LoseGoods;
import com.show.service.LoseGoodsService;
import com.show.util.GetDateUtil;
import com.show.util.JWTUtil;
import com.show.util.PageUtils;
import com.show.util.ValidationObjectUtil;
import com.show.vo.ItemInformationVo;
import com.show.vo.LoseGoodsVo;
import com.show.vo.ReceiverInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
@Slf4j
@Service
public class LoseGoodsServiceImpl implements LoseGoodsService {

    @Autowired
    private LoseGoodsMapper mapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Value("${upload.img.url}")
    private String imgUrl;

    @Override
    public Integer add(ItemInformationVo vo, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT DecodedJWT = JWTUtil.getToken(token);
        String username = DecodedJWT.getClaim("username").asString();
        LoseGoods loseGoods = new LoseGoods();
        //数据填补
        loseGoods.setId(UUID.randomUUID().toString());
        loseGoods.setDescription(vo.getDescription());
        loseGoods.setImg(vo.getImg());
        loseGoods.setFoundPeople(vo.getFoundPeople());
        loseGoods.setDepartmentId(vo.getDepartmentId());
        loseGoods.setEquipmentId(vo.getEquipmentId());
        loseGoods.setClassify(vo.getClassify());
        loseGoods.setClassifyId(vo.getClassifyId());
        loseGoods.setIsDelete(1);
        loseGoods.setRecipient(vo.getRecipient());
        /**
         * creator	varchar(255) not null  comment '记录创建人',
         *     creatorTime datetime not null  comment '创建时间',
         *     changeCreator	varchar(255) not null  comment '修改人',
         *     changeTime		datetime not null  comment '修改时间',
         */
        loseGoods.setCreator(username);
        loseGoods.setCreatorTime(GetDateUtil.getDate(new Date()));
        loseGoods.setChangeCreator(username);
        loseGoods.setChangeTime(GetDateUtil.getDate(new Date()));
        System.out.println(loseGoods);
        //进行mapper操作
        Integer add = mapper.add(loseGoods);
        if (add!=1){
            throw new InsertDateException("数据库增加出现未知异常", ResultJSON.INSERT_DATA_ERR,null);
        }
        //拾物表增加完成，增加位置和部门权重
        Integer addWeight = departmentMapper.addWeight(loseGoods.getDepartmentId());
        Integer addWeight1 = equipmentMapper.addWeight(loseGoods.getEquipmentId());
        return add;
    }

    @Override
    public PageUtils loseGoods(Map map) {
        Long aLong = mapper.selectCount(null);
        int numberOfPages =(int) map.get("numberOfPages");
        int showNumber = (int)map.get("showNumber");
        log.info("{}------{}",numberOfPages,showNumber);
        //处理分页参数
        int count=1;
        if (numberOfPages!=1){
            count = (numberOfPages*showNumber)-showNumber;
        }
        if (numberOfPages==1){
            count=0;
        }

        //处理查询参数
        showNumber = showNumber-1;
        PageUtils page = new PageUtils(count,(int)showNumber,aLong);


        map.put("numberOfPages",count);
        List<LoseGoodsVo> loseGoodsVos = mapper.queryPages(map);
        page.setList(loseGoodsVos);
        return page;
    }

    @Override
    public Integer delete(String id, String img) {
        //删除图片
        img =this.imgUrl+img;
        File imgFile = new File(img);
        System.out.println(img);
        if (imgFile.exists()){
            //存在就删除
            boolean delete = imgFile.delete();
            System.out.println(delete);
            if (!delete){
                throw new DeleteException("在删除图片中出现异常,图片没删除成功",ResultJSON.DELETE_DATA_ERR,null);
            }

        }

//        开始删除数据库
        int delete = mapper.deleteById(id);
        if (delete!=1){
            throw new DeleteException("数据库删除数据时出现异常",ResultJSON.DELETE_DATA_ERR,null);
        }

        return delete;

    }

    @Override
    public Integer updateById(ReceiverInfoVo receiverInfoVo) {
        log.info("进入服务层，方法");
        //验证手机号码
        ValidationObjectUtil.ValidationPhone(receiverInfoVo.getPhone());
        //将数据拷贝LoseGoods对象中，进行数据库修改
        LoseGoods loseGoods = new LoseGoods();
        loseGoods.setId(receiverInfoVo.getId());//id
        loseGoods.setReceiptor(receiverInfoVo.getReceiptor()); //领取人
        loseGoods.setPhone(receiverInfoVo.getPhone());//电话
        loseGoods.setDateOfClaim(GetDateUtil.getDate(new Date()));//领取时间
        loseGoods.setResponsiblePerson(receiverInfoVo.getResponsiblePerson());//经办人
        loseGoods.setWayOfClaim(Integer.valueOf(receiverInfoVo.getWayOfClaim())); //领取方式
        loseGoods.setRemark(receiverInfoVo.getRemark());//备注信息
        loseGoods.setChangeCreator(receiverInfoVo.getResponsiblePerson());//修改人
        loseGoods.setChangeTime(GetDateUtil.getDate(new Date())); //修改时间
        log.info("补全数据");
        log.info(loseGoods.toString());
        //数据补全完成
        //调用mapper层方法
        int i = mapper.addReceiverInfo(loseGoods);
        if (i!=1){
            log.error("在增加认领人的时候出异常--update");
            throw new UpdateException("增加领取人失败",ResultJSON.UPDATE_ERROR,null);
        }
        log.info("修改成功",i);

        return i;
    }


}
