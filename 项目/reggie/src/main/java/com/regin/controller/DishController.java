package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.regin.common.R;
import com.regin.dto.DishDto;
import com.regin.entity.Category;
import com.regin.entity.Dish;
import com.regin.entity.DishFlavor;
import com.regin.service.CategoryService;
import com.regin.service.DishFlavorService;
import com.regin.service.DishService;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService ;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 用于保存前端传递过来的对象数据
     * @param dishDto  保存前端的数据
     * @return r结果集
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return  R.success("新增菜品成功");
    }

    /**
     * 分页查询
     * @param page  页数
     * @param pageSize 大小
     * @param name  条件
     * @return R结果集
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //分页构造器
        Page<Dish> pageInfo = new Page(page,pageSize);
        Page<DishDto> dishDtoPage = new Page();
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(name!=null,Dish::getName,name);
        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,lambdaQueryWrapper);
        //对象拷贝 排除了所有的数据项
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        //获取原对象的数据项
        List<Dish> records = pageInfo.getRecords();
        //对原数据的数据项处理
        List<DishDto> list =  records.stream().map((item)->{
            //创建子项
            DishDto dishDto = new DishDto();
            //将原本的数据项目写入子项
            BeanUtils.copyProperties(item,dishDto);
            //通过旧数据的id查询完整的对象数据
            Long categoryId = item.getCategoryId(); //分类id
            Category byId = categoryService.getById(categoryId);
            if (byId!=null){
                String categoryName = byId.getName();
                //将查询出来的完整一个对象数据中的name赋值到子项
                dishDto.setCategoryName(categoryName);
            }


            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /**
     * 根据id查询菜品的信息和口味信息
     * @param id 菜品id
     * @return R结果集
     */
    @GetMapping("/{id}")
    public R<DishDto>get(@PathVariable Long id){
        //service执行
        DishDto byIdWithFlavor = dishService.getByIdWithFlavor(id);
        return R.success(byIdWithFlavor);
    }


    /**
     * 修改菜品数据接口
     * @param dishDto 前端传递过来的修改数据对象
     * @return R 结果集
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return  R.success("修改成功");
    }

    /**
     * 修改状态，批量修改和单次修改都是同一个请求
     * @param status 状态码 1：下架 0：上架
     * @param ids id
     * @return R结果集
     */
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable int status, Long [] ids){
        for (int i = 0; i < ids.length; i++) {
            Dish dish = new Dish();
            dish.setId(ids[i]);
            dish.setStatus(status);
            LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(Dish::getId,ids[i]);
            dishService.update(dish,queryWrapper);
        }
        return R.success("下架成功");
    }

    /**
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long [] ids){
        for (int i = 0; i < ids.length; i++) {
            dishService.removeById(ids[i]);
        }
        return R.success("删除成功");
    }
//    //根据条件查询对应菜品信息
//    @GetMapping("/list")
//        public R<List<Dish>> list(Long categoryId){
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
//        queryWrapper.eq(Dish::getCategoryId,categoryId);
//        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//        queryWrapper.eq(Dish::getStatus,1); //1表示起售状态
//        List<Dish> list = dishService.list(queryWrapper);
//        return R.success(list);
//    }


    //根据条件查询对应菜品信息
    @GetMapping("/list")
    public R<List<DishDto>> list(Long categoryId){
        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        //添加条件 where getCategoryId = categoryId
        queryWrapper.eq(Dish::getCategoryId,categoryId);
        //添加条件：排序 更具sort字段和UpdateTime
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        //添加条件：Status要等于 1
        queryWrapper.eq(Dish::getStatus,1); //1表示起售状态
        //查询出结果集
        List<Dish> dishes = dishService.list(queryWrapper);
        //对查询出来的结果集操作
        List<DishDto> list = new ArrayList<>();
         list=   dishes.stream().map((item)->{
                //创建这此要封装的对象
                DishDto dishDto = new DishDto();
                //进行拷贝
                BeanUtils.copyProperties(item,dishDto);
                //获取原数据 菜品对象中的 套餐id
                Long categoryId1 = item.getCategoryId();
                //根据 套餐id 查询出套餐对象
                Category category = categoryService.getById(categoryId1);
                if (category!=null){
                    //给套餐对象赋值，name 原数据只有 id 无法直接展示
                    String name = category.getName();;
                    dishDto.setCategoryName(name);
                }
                //当前菜品的id
                Long disId = item.getId();
                //去查询菜品的口味
                LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper();
                lambdaQueryWrapper.eq(DishFlavor::getDishId,disId);
                //查询出口味的集合
                List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
                //将值赋值给dishDto
                dishDto.setFlavors(dishFlavorList);
                return  dishDto;
            }).collect(Collectors.toList());



        return R.success(list);
    }

}
