package com.show.controller;

import com.show.util.ValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//公共接口方法
@RestController
@RequestMapping("/commons")
@Slf4j
public class CommonController {


    /**
     *    验证token接口，无逻辑，token验证会在拦截器完成
     *    在前端的前置路由守卫会访问这个接口，如果没有返回正常值，不跳转路由
     * @return
     */
    @GetMapping("/verificationToken")
    public ResultJSON verificationToken(){
        log.info("token验证完成");
        return new ResultJSON("token验证完成",ResultJSON.VERIFICATION_TOKEN_OK,null);
    }
}
