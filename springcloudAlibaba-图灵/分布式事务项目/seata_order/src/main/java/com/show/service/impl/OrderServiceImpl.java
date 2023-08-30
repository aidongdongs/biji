package com.show.service.impl;

import com.show.feign.StockServiceFeign;
import com.show.mapper.OrderMapper;
import com.show.pojo.Order;
import com.show.pojo.Stock;
import com.show.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockServiceFeign stockServiceFeign;

    @GlobalTransactional
    @Override
    public Integer insert(Order order) {
        String insert = stockServiceFeign.insert(new Stock(null,"30"));
        int i =1/0;
        orderMapper.insert(order);
        return Integer.valueOf(insert);
    }
}
