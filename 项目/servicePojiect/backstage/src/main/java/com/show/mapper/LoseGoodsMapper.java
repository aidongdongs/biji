package com.show.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.show.pojo.LoseGoods;
import com.show.vo.LoseGoodsVo;

import java.util.List;
import java.util.Map;

public interface LoseGoodsMapper extends BaseMapper<LoseGoods> {
    Integer add(LoseGoods loseGoods);

    List<LoseGoodsVo> queryPages(Map map);

    Integer addReceiverInfo(LoseGoods loseGoods);
}
