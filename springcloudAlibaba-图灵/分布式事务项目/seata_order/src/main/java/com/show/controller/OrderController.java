package com.show.controller;

import com.show.mapper.OrderMapper;
import com.show.pojo.Order;
import com.show.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/insert")
    public String insert (Order order){
        Integer insert = orderService.insert(order);
        return insert.toString();
    }


    @RequestMapping("/dome")
    public List<Order> dome(){
        return orderMapper.list();
    }


}
