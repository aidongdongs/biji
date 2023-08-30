package com.regin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.entity.DishFlavor;
import com.regin.mapper.DishFlavorMapper;
import com.regin.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
