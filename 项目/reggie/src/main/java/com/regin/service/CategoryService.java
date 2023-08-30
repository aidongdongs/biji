package com.regin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.regin.entity.Category;
import com.regin.entity.Employee;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
