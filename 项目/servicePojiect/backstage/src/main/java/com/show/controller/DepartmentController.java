package com.show.controller;

import com.show.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;


    @GetMapping("/getAll")
    public ResultJSON getAll(){

        return new ResultJSON("查询成功",1,service.getAll());
    }
}
