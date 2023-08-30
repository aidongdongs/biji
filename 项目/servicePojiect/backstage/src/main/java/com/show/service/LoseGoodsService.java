package com.show.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.show.pojo.LoseGoods;
import com.show.util.PageUtils;
import com.show.vo.ItemInformationVo;
import com.show.vo.ReceiverInfoVo;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface LoseGoodsService  {
    Integer add(ItemInformationVo itemInformationVo, HttpServletRequest request);

   com.show.util.PageUtils loseGoods(Map map);

    Integer delete(String id ,String img);


    Integer updateById(ReceiverInfoVo receiverInfoVo);
}
