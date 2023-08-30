package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.regin.common.R;
import com.regin.entity.Category;
import com.regin.entity.Dish;
import com.regin.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类的管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category 前端传递过来的新增对象数据
     * @return r结果集
     */
    @PostMapping
    public R<String> save( @RequestBody Category category){
        categoryService.save(category);
        log.info("categroy"+category);
        return R.success("新增分类成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page , int pageSize){
        //分页构造器
        Page<Category> page1 = new Page(page,pageSize);
        //调教构造
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.orderByDesc(Category::getSort);
        //进行分页查询
        categoryService.page(page1,queryWrapper);

        return R.success(page1);
    }
    @DeleteMapping()
    public R delete( Long ids){
        categoryService.remove(ids);
        return R.success("分类信息删除成功");
    }

    /**
     * 修改员工信息
     * @param category 前端传递过来的员工json数据
     * @return
     */
    @PutMapping
    public R update(@RequestBody Category category){
            categoryService.updateById(category);
        return R.success("修改成功");
    }

    /**
     * 根据条件查询查询分类信息
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> lists( Category category){
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        //添加条件
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        //查询
        List<Category> list = categoryService.list(queryWrapper);
        return  R.success(list);
    }

}
