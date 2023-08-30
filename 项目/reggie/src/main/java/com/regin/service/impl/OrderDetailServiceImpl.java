package com.regin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.entity.OrderDetail;
import com.regin.mapper.OrderDetailMapper;
import com.regin.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
