package com.show.controller;

import com.show.service.ISupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/route")
@Slf4j
public class RouteController {

    @Autowired
    private ISupplierService supplierService;

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/toLogin")
    public ModelAndView toLogin(){
        log.info("跳转登录");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
        return modelAndView;
    }

    /**
     * 跳转增加商品
     * @return
     */
    @GetMapping("/supplier/toAdd")
    public ModelAndView toAddSupplier(){
        System.out.println(11111);
        log.info("跳转增加商品");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/supplier/add.jsp");
        return modelAndView;
    }


}
