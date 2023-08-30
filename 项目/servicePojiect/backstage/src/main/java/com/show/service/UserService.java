package com.show.service;

import com.show.vo.LoginVo;
import com.show.vo.RegisterVo;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 验证用户是否唯一的service
     * @param username 传递过来的用户名
     * @return 是否找到用户
     */
    Boolean getByUsername(String username);

    /**
     * 增加一个用户
     * 系统注册接口
     * @param registerVo s
     * @param request 需要用到session中验证码
     * @return 数据库受影响的函数
     */
    Integer addUser(RegisterVo registerVo, HttpServletRequest request);

    String login (LoginVo loginVo,HttpServletRequest request);
}
