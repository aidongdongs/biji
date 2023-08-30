package com.regin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.entity.ShoppingCart;
import com.regin.mapper.ShoppingCartMapper;
import com.regin.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper,ShoppingCart> implements ShoppingCartService{
}
