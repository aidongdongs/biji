package com.regin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.common.CustomException;
import com.regin.entity.Category;

import com.regin.entity.Dish;
import com.regin.entity.Setmeal;
import com.regin.mapper.CategoryMapper;
import com.regin.service.CategoryService;
import com.regin.service.DishService;
import com.regin.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category>implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品，如果已经关联，抛出业务异常
        //创建查询条件对象
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        //将条件加入条件对象
        queryWrapper.eq(Dish::getCategoryId,id);
        //进行统计查询
        int count = dishService.count(queryWrapper);
        //判断统计数据
        if (count>0){
        //已经关联菜品，需要抛出业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        //查询当前分类是否关联了套餐，如果已经关联，抛出业务异常
        //创建查询条件对象
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper();
        //将条件加入条件对象
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        //进行统计查询
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        //判断统计数据
        if (count1>0){
            //已经关联套餐，需要抛出业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        //删除分类
        super.removeById(id);
    }
}
