package com.show.controller;

import com.show.pojo.StorageRecord;
import com.show.pojo.Supplier;
import com.show.service.IStorageRecordService;
import com.show.service.ISupplierService;
import com.show.utill.PageUtils;
import com.show.vo.StorageRecordVo;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sys/storageRecord")
public class StorageRecordController {

    @Autowired
    private IStorageRecordService service;

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/list")
    public ModelAndView list(String queryGoodsName,String queryPayStatus, Integer pageIndex,String querySupplierId){


        ModelAndView modelAndView  = new ModelAndView();
        PageUtils pages = service.pages(pageIndex==null?0:pageIndex, 3,queryGoodsName,queryPayStatus,querySupplierId);
        pages.setShowNumber(pageIndex);
        addModel(modelAndView,pages);
        modelAndView.setViewName("/WEB-INF/jsp/storageRecord/list.jsp");
        return modelAndView;
    }


    @ResponseBody
    @DeleteMapping("/del/{id}")
    public Result delete (@PathVariable("id") String id ) {
        Result result = new Result();
        if (service.delete(id)==1) {

            result.setDelResult("true");
            return result;
        }else {
            result.setDelResult("false");
            return result;
        }
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        StorageRecordVo storageRecord = service.queryById(id);
        modelAndView.addObject("storageRecord",storageRecord);
        modelAndView.setViewName("/WEB-INF/jsp/storageRecord/view.jsp");
        return modelAndView;
    }

    @GetMapping("/toUpdate/{id}")
    public ModelAndView toUpdate(@PathVariable String id){
        ModelAndView modelAndView  = new ModelAndView();
        System.out.println("id"+id);

        //数据放入
        modelAndView.addObject("storageRecord",service.queryById(id));
        modelAndView.setViewName("/WEB-INF/jsp/storageRecord/update.jsp");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update (StorageRecord storageRecord, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(storageRecord);
        Integer userId = service.update(storageRecord, session.getAttribute("userId").toString());
        if (userId==1){
            modelAndView.setViewName("/WEB-INF/jsp/storageRecord/list.jsp");
            PageUtils pages = service.pages(0, 3,null,null,null);
            addModel(modelAndView,pages);

        }else{
            modelAndView.addObject("message","修改错误");
            modelAndView.setViewName("/WEB-INF/jsp/storageRecord/update.jsp");
        }
        return modelAndView;
    }

    /**
     * 跳转增加
     * @return
     */
    @GetMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/storageRecord/add.jsp");
        return modelAndView;
    }

    @PostMapping("/add")
    public void add(StorageRecord storageRecord, HttpSession session, HttpServletResponse response){

        String id = session.getAttribute("userId").toString();
        Integer add = service.add(storageRecord, id);
        if (add==1){
            //增加成功
            try {
                response.sendRedirect("http://localhost:8080/controller_war/sys/storageRecord/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{

        }
    }


    /**
     * 往model中添加数据
     * @param modelAndViewName
     * @param pages
     */
    public void addModel(ModelAndView modelAndViewName , PageUtils pages){
        Integer showNumber = pages.getShowNumber();
        if(showNumber==null){
            showNumber=1;
        }else{
            if (pages.getList().size()==0){
                showNumber=0;
            }
        }
        List<Supplier> suppliers = supplierService.queryAll();
        modelAndViewName.addObject("storageRecordList",pages.getList());
        modelAndViewName.addObject("totalCount",pages.getTotalNum());
        modelAndViewName.addObject("currentPageNo",showNumber);
        modelAndViewName.addObject("totalPageCount",pages.getTotalPage());
        modelAndViewName.addObject("supplierList",suppliers);
    }
}
