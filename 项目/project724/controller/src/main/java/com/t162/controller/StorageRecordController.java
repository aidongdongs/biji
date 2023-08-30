package com.t162.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.t162.domain.Result;
import com.t162.domain.StorageRecord;
import com.t162.domain.Supplier;
import com.t162.service.storageRecord_service.StorageRecordService;
import com.t162.service.supplier_service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys/storageRecord")
public class StorageRecordController {
    @Autowired
    private StorageRecordService recordService;
    @Autowired
    private SupplierService service;
    @RequestMapping("/list")
    public String selectAll(Integer pageIndex, HttpSession session, HttpServletRequest request, StorageRecord sto) {
        PageHelper.offsetPage(pageIndex==null?0:(pageIndex-1)*5,5);
        List<StorageRecord> storageRecords = recordService.selectAllStorageRecord(sto);
        List<Supplier> suppliers = service.selectSupplierName();
        session.setAttribute("supplierList",suppliers);
        PageInfo pageInfo=new PageInfo(storageRecords);
        request.setAttribute("pageInfo",pageInfo);
        return "storageRecord/list";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "storageRecord/add";
    }

    @PostMapping("/add")
    public String add(StorageRecord sto){
        int i = recordService.addStorageRecord(sto);
        System.out.println(i);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/storageRecord/list";
        }
        return "sysError";
    }
    @ResponseBody
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable("id") Integer id){
        int i = recordService.delById(id);
        if (i==1){
            return new Result("true");
        }
        return new Result("false");

    }

    @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        StorageRecord storageRecord = recordService.selectById(id);
        modelAndView.setViewName("storageRecord/view");
        modelAndView.addObject("storageRecord",storageRecord);
        return modelAndView;
    }

    @RequestMapping("/toUpdate/{id}")
    public ModelAndView update(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        StorageRecord storageRecord = recordService.selectById(id);
        modelAndView.setViewName("storageRecord/update");
        modelAndView.addObject("storageRecord",storageRecord);
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateSupplier(StorageRecord sto){
        int i = recordService.updateById(sto);
        System.out.println(i);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/storageRecord/list";
        }
        return "sysError";
    }
}
