package com.show.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    @PostMapping("/zc")
    public ResultJSON test(){
        System.out.println("11111111111111");
        return new ResultJSON("成功",1111,null);
    }
}
