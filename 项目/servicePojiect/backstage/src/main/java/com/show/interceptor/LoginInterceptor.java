package com.show.interceptor;

import com.show.controller.ResultJSON;
import com.show.ex.ParameterNullException;
import com.show.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器");
        String token = request.getHeader("token");
        log.info("token值--{}",token);
        if (token == null) {
            log.error("没有传递token，没有令牌");
            throw new ParameterNullException("没有传递token，没有令牌", ResultJSON.PARAMETER_ERR, null);
        }
        //验证token
        JWTUtil.verify(token);
        log.info("拦截器验证完成");
        return  true;
    }



}
