package com.show.controller;

import com.show.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService service;
    @GetMapping("/getAll")
    public ResultJSON getAll(){
        System.out.println(service.getAll());
        return new  ResultJSON("查询成功",111,service.getAll());
    }
}
