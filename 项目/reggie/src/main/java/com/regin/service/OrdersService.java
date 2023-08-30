package com.regin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.regin.entity.Orders;

public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
