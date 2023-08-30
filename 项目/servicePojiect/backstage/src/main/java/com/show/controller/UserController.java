package com.show.controller;


import com.show.ex.UserNameUniqueException;
import com.show.service.UserService;
import com.show.vo.LoginVo;
import com.show.vo.RegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@CrossOrigin()
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 验证用户是唯一的接口
     * 用于注册的时候使用
     * 给前端校验
     * @param username 用户注册时提交过来的用户名
     * @return 是否是唯一的结果集
     */
    @GetMapping("/VerifyThatTheUserIsUnique")
    public ResultJSON VerifyThatTheUserIsUnique(String username,HttpServletRequest request){
        //验证参数为空抛出异常，跳转全局异常处理
        if (username==null){
            throw new UserNameUniqueException("用户名已经存在",ResultJSON.PARAMETER_ERR,null);
        }
        //查询用户是否存在
        Boolean byUsername = userService.getByUsername(username);
        //根据结果进行返回数据
        if(byUsername){
            return new ResultJSON("用户存在，无法注册",ResultJSON.VerifyThatTheUserIsUnique_ERR,byUsername);
       }else{
            return new ResultJSON("用户不存在，可以注册",ResultJSON.VerifyThatTheUserIsUnique_OK,byUsername);
       }
    }

    /**
     * 注册接口
     * 将所有的逻辑处理判断，封装在了service层
     * 只要不出现异常，就会走完代码
     * @param registerVo 前端传递过来个注册表单的data数据
     * @param request 验证码在session中需要request获取session
     * @return
     */
    @PostMapping("/register")
    public ResultJSON register(@RequestBody RegisterVo registerVo,HttpServletRequest request){
        Integer integer = userService.addUser(registerVo, request);
        return new ResultJSON("注册成功",ResultJSON.REGISTER_USER_NAME_SUCCESS,integer);
    }

    @PostMapping("/login")
    public ResultJSON login(@RequestBody LoginVo loginVo,HttpServletRequest request){
        log.info("登录信息-{}",loginVo);
        String token = userService.login(loginVo, request);
        System.out.println(token);
            return new ResultJSON("登录成功",ResultJSON.LOGIN_SUCCESS,token);
    }
}
