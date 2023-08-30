package com.regin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.regin.dto.DishDto;
import com.regin.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入口味，需要同时操作两张表，
    public  void saveWithFlavor(DishDto dishDto);

    //根据id来查询菜品的信息和对饮的口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息 同时跟新口味信息
    public       void   updateWithFlavor(DishDto dishDto);
}
