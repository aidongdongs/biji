package com.regin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.common.CustomException;
import com.regin.dto.SetmealDto;
import com.regin.entity.Setmeal;
import com.regin.entity.SetmealDish;
import com.regin.mapper.SetmealMapper;
import com.regin.service.SetmealDishService;
import com.regin.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper,Setmeal> implements SetmealService {

    @Autowired
   private SetmealDishService setmealDishService;

    /**
     * 保存套餐
     * @param dto 前端传递的保存套餐数据
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto dto) {
        //保存套餐的基本信息 setmeal
        this.save(dto);
        List<SetmealDish> setmealDishes = dto.getSetmealDishes();
        for (int i = 0; i < setmealDishes.size(); i++) {
            SetmealDish setmealDish = setmealDishes.get(i);
            setmealDish.setSetmealId(dto.getId());
        }
        //保存套餐表菜品的关联信息 setmeal——dish 操作
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐
     * @param list 传递过来需要删除套餐的id
     */
    @Override
    public void deleteWithDish(Long [] list) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId,list);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(queryWrapper);
        //需要停售才能删除
        if (count>0){
        //如果不能删除，抛出业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }
        //删除套餐表中数据

        removeByIds( Arrays.asList(list));
        //删除关联数据
        LambdaQueryWrapper<SetmealDish>queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(SetmealDish::getSetmealId,list);
        setmealDishService.remove(queryWrapper1);
    }

    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //修改套餐
        //修改套餐内容
        super.updateById(setmealDto);
        //修改套餐关联信息

        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        this.setmealDishService.remove(queryWrapper);
        //插入 插入的数据中么有绑定套餐id需要自己绑定
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (int i = 0; i < setmealDishes.size(); i++) {
            SetmealDish setmealDish = setmealDishes.get(i);
            setmealDish.setSetmealId(setmealDto.getId());
        }
        //插入数据
        this.setmealDishService.saveOrUpdateBatch(setmealDishes);


    }
}
