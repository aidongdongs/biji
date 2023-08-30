package com.regin.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.regin.dto.SetmealDto;
import com.regin.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    //新增套餐， 保存套餐的关联关系
    public void  saveWithDish(SetmealDto dto);

    //删除套餐，删除套餐的关联关系
    public void deleteWithDish(Long [] list);
    //删除套餐
    public void updateWithDish(SetmealDto setmealDto);
}
