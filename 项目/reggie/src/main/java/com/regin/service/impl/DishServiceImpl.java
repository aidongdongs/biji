package com.regin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.common.R;
import com.regin.dto.DishDto;
import com.regin.entity.Dish;
import com.regin.entity.DishFlavor;
import com.regin.mapper.DishMapper;
import com.regin.service.DishFlavorService;
import com.regin.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * 新增菜品，同时保存口味数据
     * @param dishDto 数据信息
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
         //保存菜品的基本信息到菜品表dish
        this.save(dishDto);
        Long disId = dishDto.getId();
        //爆粗菜品口味表 dishFlavor
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (int i = 0; i < flavors.size(); i++) {
            DishFlavor dishFlavor = flavors.get(i);
            dishFlavor.setDishId(disId);
        }
        dishFlavorService.saveBatch(dishDto.getFlavors());

    }

    /**
     * 实现思路
     * 需要查询两张表
     * 1：通过id查询菜品表 dish 查询出具体对象
     * 2：创建返回结果集 dishDto
     * 3：将查询出来的dish对象浅拷贝到dishDto中
     * 4：根据id查询DishFlavor表 查询出结果list集合
     * 5：将list结果集手动放入 dishDto结果集中去
     * 5：返回结果集
     * 根据id查询菜品的基本信息和口味信息
     * @param id 菜品id
     * @return 结果对象
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //返回的结果集
        DishDto dishDto = new DishDto();
        //查询菜品基本信息
        Dish dish = this.getById(id);
        //对象拷贝
        BeanUtils.copyProperties(dish,dishDto);
        //查询口味表
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    /**
     *实现思路
     * 1：更新菜品表的数据
     * 2：因为是修改数据，口味数据可能涉及到新的数据插入，所有先删除，再插入
     * 3：删除菜品口味中的全部数据
     * 4：处理前端传递过来的口味数据中不存在dishid的值，进行插入
     * 5：插入前端传递过来的口味项
     *
     * @param dishDto 前端传递过来修改菜品的对象数据
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表
            super.updateById(dishDto);
        /**
         * 更新口味表
         * 1：删除当前菜品的全部口味
         * 2：添加新传递过来的口味
         */
        //1：删除数据
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //2：插入数据
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (int i = 0; i < flavors.size(); i++) {
            DishFlavor dishFlavor = flavors.get(i);
            dishFlavor.setDishId(dishDto.getId());
        }
        dishFlavorService.saveBatch(flavors);
    }

}
