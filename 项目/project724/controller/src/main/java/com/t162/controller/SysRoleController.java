package com.t162.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.t162.domain.Result;
import com.t162.domain.Supplier;
import com.t162.domain.SysRole;
import com.t162.domain.SysUser;
import com.t162.service.role_service.RoleService;
import com.t162.service.sysuser_service.SysUserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SysRoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private SysUserService service;

    @RequestMapping("/sys/role/list")
    public String selectAll(HttpServletRequest request) {

        List<SysRole> sysRoles = roleService.selectRoleName();

        request.setAttribute("roles",sysRoles);
        return "sysRole/list";
    }
    @RequestMapping("/sys/role/toAdd")
    public String toAdd(){
        return "sysRole/add";
    }

    @ResponseBody
    @RequestMapping("/sys/role/codeExist")
    public Result findCode(String code){
        String code1 = roleService.findCode(code);
        if (code1!=null){
            return new Result(1);
        }
        return new Result(0);
    }
    @PostMapping("/sys/role/add")
    public String add(SysRole sysRole, HttpSession session){
        SysUser user = (SysUser) session.getAttribute("userSession");
        Integer id = user.getId();
        sysRole.setCreatedUserId(id);
        int i = roleService.addUser(sysRole);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/user/list";
        }
        return "sysError";
    }

    @ResponseBody
    @DeleteMapping("/sys/role/del/{id}")
    public Result del(@PathVariable("id") Integer id){
        int i = service.selectByRoleId(id);
        if (i!=0){
            return new Result("false");
        }
        int delUser = roleService.delUser(id);
        if (delUser==1){
            return new Result("true");
        }
        return new Result("notexist");
    }

    @RequestMapping("sys/role/toUpdate/{id}")
    public ModelAndView toUpdatePwd(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();
        SysRole sysRole = roleService.selectSysRoleById(id);
        modelAndView.setViewName("sysRole/update");
        modelAndView.addObject("sysRole",sysRole);
        return modelAndView;
    }
    @PostMapping("/sys/role/update")
    public String updateSupplier(SysRole sysRole){
        int i = roleService.updateRole(sysRole);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/role/list";
        }
        return "sysError";
    }
}
