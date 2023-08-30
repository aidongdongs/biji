package com.t162.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.t162.domain.*;
import com.t162.service.role_service.RoleService;
import com.t162.service.sysuser_service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SysUserController {
    @Autowired
    private SysUserService service;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/login")
    public String login(SysUser sysUser, HttpServletRequest request, HttpSession session){
        SysUser user = service.login(sysUser);
        if(user != null){
            session.setAttribute("userSession",user);
            return "frame";
        }
        request.setAttribute("error","用户名或密码错误！");

        return "login";
    }
    @RequestMapping("/sys/user/list")
    public String selectAll(Integer pageIndex, HttpSession session, HttpServletRequest request, SysUser user) {
        PageHelper.offsetPage(pageIndex==null?0:(pageIndex-1)*5,5);
        List<SysUser> sysUsers = service.selectAll(user);
        PageInfo pageInfo=new PageInfo(sysUsers);
        request.setAttribute("pageInfo",pageInfo);
        List<SysRole> sysRoles = roleService.selectRoleName();
        session.setAttribute("roleList",sysRoles);
        return "sysUser/list";
    }
    @RequestMapping("sys/user/toAdd")
    public String toAdd(){
        return "sysUser/add";
    }

    @PostMapping("/sys/user/add")
    public String add(SysUser sysUser,String dateBirthday){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = ft.parse(dateBirthday);
            sysUser.setBirthday(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int i = service.addAll(sysUser);
        System.out.println(i);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/user/list";
        }
        return "sysError";
    }

    @RequestMapping("sys/user/view/{id}")
    public ModelAndView view(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        SysUser user = service.selectById(id);
        modelAndView.setViewName("sysUser/view");
        modelAndView.addObject("sysUser",user);
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("sys/user/userExist")
    public Result selectByAccount(String account){
        String s = service.selectByIdAccount(account);
        if (s!=null){
            return new Result(1);
        }
        return new Result(0);
    }

    @ResponseBody
    @DeleteMapping("/sys/user/del/{id}")
    public Result del(@PathVariable("id") Integer id){
        int i = service.delById(id);
        if (i==1){
            return new Result("true");
        }
        return new Result("false");

    }

    @RequestMapping("sys/user/toUpdate")
    public ModelAndView update(Integer uid){
        ModelAndView modelAndView=new ModelAndView();
        SysUser sysUser = service.selectById(uid);
        modelAndView.setViewName("sysUser/update");
        modelAndView.addObject("sysUser",sysUser);
        return modelAndView;
    }
    @PostMapping("sys/user/update")
    public String updateUser(SysUser user,String dateBirthday){

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = ft.parse(dateBirthday);
            user.setBirthday(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int i = service.updateUserById(user);
        if (i==1){
            return "redirect:http://localhost:8080/controller/sys/user/list";
        }
        return "sysError";
    }


    @RequestMapping("sys/user/toUpdatePwd")
    public ModelAndView toUpdatePwd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("sysUser/updatePassword");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/sys/user/checkOldPwd")
    public Result selectByIdAndPassword(String oldPassword,HttpSession session){
        SysUser user = (SysUser) session.getAttribute("userSession");
        System.out.println(user.getId());
        System.out.println(oldPassword);
        if (user.getId()==null){
            return new Result("sessionerror");
        }
        if (oldPassword==null ||oldPassword== ""){
            return new Result("error");
        }
        String s = service.selectByIdAndPassword(user.getId(), oldPassword);
        if (s!=null){
            return new Result("true");
        }
        return new Result("false");
    }
        @RequestMapping("user/savePassword")
        public String updatePasswordById(Integer id,String newPassword){
            int i = service.updatePassById(id, newPassword);
            if (i==1){
                return "redirect:http://localhost:8080/controller/sys/user/list";
            }
            return "sysError";
        }
}
