package com.show.service;

import com.show.pojo.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {


    @Transactional
    Integer insert(Order order);
}
