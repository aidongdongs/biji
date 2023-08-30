package com.t162.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.t162.domain.Result;
import com.t162.domain.Supplier;
import com.t162.service.supplier_service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/sys/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;

    @RequestMapping(value = "/list")
    public String selectAll(Integer pageIndex, HttpServletRequest request,Supplier supplier) {
        PageHelper.offsetPage(pageIndex==null?0:(pageIndex-1)*5,5);
        List<Supplier> suppliers = service.selectSupplier(supplier);
        PageInfo pageInfo=new PageInfo(suppliers);
        request.setAttribute("pageInfo",pageInfo);
        return "supplier/list";
    }

    @ResponseBody
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable("id") Integer id){
        int del = service.del(id);
        if (del==1){
            return new Result("true");
        }
        return new Result("false");

    }
   @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Supplier supplier = service.selectById(id);
        modelAndView.setViewName("supplier/view");
        modelAndView.addObject("supplier",supplier);
        return modelAndView;
    }
    @RequestMapping("/toUpdate/{id}")
    public ModelAndView update(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Supplier supplier = service.selectById(id);
        modelAndView.setViewName("supplier/update");
        modelAndView.addObject("supplier",supplier);
        return modelAndView;
    }
     @PostMapping("/update")
        public String updateSupplier(Supplier supplier){
        int i = service.updateSupplier(supplier);
        System.out.println(i);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/supplier/list";
        }
       return "sysError";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "supplier/add";
    }

    @PostMapping("/add")
    public String add(Supplier supplier){
        int add = service.add(supplier);
        System.out.println(add);
        if (add==1){
            return "redirect:http://localhost:8080/controller/sys/supplier/list";
        }
        return "sysError";
    }
}
