package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.regin.common.R;

import com.regin.entity.Orders;
import com.regin.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        System.out.println(111111);
        ordersService.submit(orders);
        return R.success("成功");
    }

    @GetMapping("/userPage")
    public R<Page> userPage(int page, int pageSize, HttpSession session){
       Long userId = (Long) session.getAttribute("user");
        Page<Orders> pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Orders::getUserId,userId);
        ordersService.page(pageInfo);
        return R.success(pageInfo);
    }
}
