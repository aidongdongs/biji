package com.show.controller;

import com.show.pojo.Role;
import com.show.service.IRoldService;
import com.show.utill.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class RoleController {


    @Autowired
    private IRoldService roldService;

    /**
     * 查询全部角色
     * @return
     */
    @GetMapping("/simpleList")
    @ResponseBody
    public List<Role> simpleList (){
       return roldService.queryAll();
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/sysRole/list.jsp");
        modelAndView.addObject("roleList",roldService.queryAll());
        return modelAndView;
    }

    @GetMapping("/toUpdate/{id}")
    public ModelAndView toUpdate(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();

        Role role = roldService.queryById(id);
        modelAndView.addObject("sysRole",role);
        modelAndView.setViewName("/WEB-INF/jsp/sysRole/update.jsp");
        return modelAndView;
    }

    @PostMapping("/update")
    public void update(Role role, HttpSession session, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        String id = session.getAttribute("userId").toString();
        if (roldService.update(role,id)){
            try {
                response.sendRedirect("http://localhost:8080/controller_war/sys/role/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @GetMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/sysRole/add.jsp");
        return modelAndView;
    }

    /**
     * 验证身份编码是否存在
     * @param code
     * @return
     */
    @ResponseBody
    @GetMapping("/codeExist")
    public Result codeExist(String code){
        Integer integer = roldService.queryByCodeCount(code);
        Result result = new Result();
        result.setExist(integer);
        return result;
    }

    @PostMapping("/add")
    public void add(String code,String roleName,HttpSession session,HttpServletResponse response){
        String id = session.getAttribute("userId").toString();
        if(roldService.add(code,roleName,id)){
            try {
                response.sendRedirect("http://localhost:8080/controller_war/sys/role/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @ResponseBody
    @DeleteMapping("/del/{id}")
    public Result delete (@PathVariable("id") String id ) {
        Result result =new Result();
        Integer delete = roldService.delete(id);
        if (delete==1){
            result.setDelResult("true");
        }else{
            result.setDelResult("false");
        }

        return result;
    }


}
