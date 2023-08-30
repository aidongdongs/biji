package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.regin.common.R;
import com.regin.dto.SetmealDto;
import com.regin.entity.Setmeal;
import com.regin.entity.SetmealDish;
import com.regin.service.SetmealDishService;
import com.regin.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
   private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 保存套餐 ， 同时操作两张表，逻辑封装在service层
     * @param SetmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto SetmealDto){
        setmealService.saveWithDish(SetmealDto);
        return R.success("保存成功");
    }

    /**
     * 分页展示套餐
     * @param page  页码
     * @param pageSize 展示条数
     * @param name 名字查询
     * @return R<SetmealDto>
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> copyPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo);

        BeanUtils.copyProperties(page,copyPage,"records");
            //获取本集合
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = new ArrayList<>();
        //操作原集合
//        list  = records.stream().map((item)->{
//            SetmealDto setmealDto = new SetmealDto();
//            BeanUtils.copyProperties(  item,setmealDto);
//            Setmeal byId = setmealService.getById(item.getId());
//            if (byId!=null){
//                setmealDto.setCategoryName(byId.getName() );
//            }
//            return setmealDto;
//        }).collect(Collectors.toList());
        for (int i = 0; i < records.size(); i++) {
            SetmealDto dto = new SetmealDto();
            Setmeal setmeal = records.get(i);
            BeanUtils.copyProperties(setmeal,dto);
            Setmeal byId = setmealService.getById(setmeal.getId());
            if (byId!=null){
                dto.setCategoryName(byId.getName());
            }
            list.add(dto);
        }

        copyPage.setRecords(list);
        return  R.success(copyPage);
    }

    /**
     * 删除套餐，以及关联的数据 操作两张表，逻辑封装在业务层
     * @param ids
     * @return  消息
     */
    @DeleteMapping
    public R<String> delete (@RequestParam Long [] ids){

        //调用删除逻辑
        setmealService.deleteWithDish(ids);
    return R.success("套餐数据删除成功");
//        return  null;
    }

    @PostMapping("/status/{status}")
    public  R<String> status( @PathVariable int status,@RequestParam Long [] ids){
        List  <Setmeal> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Setmeal setmeal = new Setmeal();
            setmeal.setStatus(status);
            setmeal.setId(ids[i]);
            list.add(setmeal);
        }
        setmealService.updateBatchById(list);
        return R.success("修改成功");
    }
    @GetMapping("/{id}")
    public R<SetmealDto> getSetmealDto(@PathVariable Long id){
        SetmealDto dto = new SetmealDto();
        //查询套餐
        Setmeal byId = setmealService.getById(id);
        //查询套餐菜品
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetmealDish::getSetmealId,id);
        List<SetmealDish> list = setmealDishService.list(queryWrapper);
        dto.setSetmealDishes(list);
        BeanUtils.copyProperties(byId,dto);

        return R.success(dto);
    }

    /**
     * 修改套餐
     * @param dto 前端传递过来的套餐json数据
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody SetmealDto dto){
        setmealService.updateWithDish(dto);
        return R.success("删除成功");
//        return null;
    }

    /**
     * 根据id 和 satus 来查询套餐数据
     * @param categoryId 套餐id
     * @param status 套餐状态
     * @return
     */
    @GetMapping("/list")
    public  R<List<Setmeal>> list(Long categoryId ,int status){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getCategoryId,categoryId).eq(Setmeal::getStatus,status);
        List<Setmeal> list = setmealService.list(queryWrapper);

        return R.success(list);
    }



}
