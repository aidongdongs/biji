package com.show.controller;

import com.show.pojo.Supplier;
import com.show.service.ISupplierService;
import com.show.utill.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
@RequestMapping("/sys/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @ResponseBody
    @DeleteMapping("/del/{id}")
    public Result delete (@PathVariable("id") String id ) {
        Result result =new Result();
        if (supplierService.delete(id)) {
            result.setDelResult("true");
            return result;
        }else {
            result.setDelResult("false");
            return result;
        }
    }

    @GetMapping("/list")
    public ModelAndView list (String querySupCode, String querySupName , Integer pageIndex){
        PageUtils pages = supplierService.pages(pageIndex==null?0:pageIndex, 3,querySupCode,querySupName);
        pages.setShowNumber(pageIndex);
        //拿到数据
        ModelAndView modelAndViewName = new ModelAndView();
        addModel(modelAndViewName,pages);
        modelAndViewName.setViewName("/WEB-INF/jsp/supplier/list.jsp");
        return modelAndViewName;
    }

    /**
     * 跳转到修改商品
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public ModelAndView toUpdate(@PathVariable String id){
        ModelAndView modelAndView  = new ModelAndView();
        //数据放入
        modelAndView.addObject("supplier",supplierService.queryById(id));
        modelAndView.setViewName("/WEB-INF/jsp/supplier/update.jsp");
        return modelAndView;
    }

    @GetMapping("/simpleList")
    @ResponseBody
    public List<Supplier> simpleList(){
       return supplierService.queryAll();
    }

    /**
     * 跳转展示商户页面
     * @return
     */
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("supplier",supplierService.queryById(id));
        modelAndView.setViewName("/WEB-INF/jsp/supplier/view.jsp");
        return modelAndView;
    }

    /**
     * 修改商户
     */
    @PostMapping("/update")
    public ModelAndView update (Supplier supplier, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Boolean update = supplierService.update(supplier,session.getAttribute("userId").toString());
        if (update){
            modelAndView.setViewName("/WEB-INF/jsp/supplier/list.jsp");
            PageUtils pages = supplierService.pages(0, 3,null,null);
            addModel(modelAndView,pages);

        }else{
            modelAndView.addObject("message","修改错误");
            modelAndView.setViewName("/WEB-INF/jsp/supplier/update.jsp");
        }
        return modelAndView;
    }

    @PostMapping("/add")
    public void add (Supplier supplier, HttpSession session, HttpServletResponse response){
        String uid = session.getAttribute("userId").toString();
        Integer add = supplierService.add(supplier, uid);
        if (add==1){
            try {
                response.sendRedirect("http://localhost:8080/controller_war/sys/supplier/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 往model中添加数据
     * @param modelAndViewName
     * @param pages
     */
    public void addModel(ModelAndView modelAndViewName , PageUtils pages){
        Integer showNumber = pages.getShowNumber();
        System.out.println(showNumber+"------");
        System.out.println(pages.getList().toString());
         if(showNumber==null){
             showNumber=1;
         }else{
             if (pages.getList().size()==0){
                 showNumber=0;
             }
         }
        modelAndViewName.addObject("supplierList",pages.getList());
        modelAndViewName.addObject("totalCount",pages.getTotalNum());
        modelAndViewName.addObject("currentPageNo",showNumber);
        modelAndViewName.addObject("totalPageCount",pages.getTotalPage());
        modelAndViewName.setViewName("/WEB-INF/jsp/supplier/list.jsp");
    }
}
