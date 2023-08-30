package com.show.common;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.show.controller.ResultJSON;
import com.show.ex.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody //返回结果为json
public class GlobalException {
    /**
     * 自定义异常处理器
     * @param exception
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResultJSON exceptionHandler(BaseException exception){
        return exception.result();
    }

    /**
     * jwt异常处理器
     * @param e
     * @return
     */

    @ExceptionHandler(JWTVerificationException.class)
    public  ResultJSON exceptionHandlerJWT(JWTVerificationException e ){
        log.error("token异常处理器");
        e.printStackTrace();
        if (e instanceof TokenExpiredException){
            return new ResultJSON("token过期",ResultJSON.TOKEN_ERROR,null);
        }else if (e instanceof SignatureVerificationException){
            return new ResultJSON("签名错误",ResultJSON.TOKEN_ERROR,null);
        }else if (e instanceof AlgorithmMismatchException){
            return new ResultJSON("算法不匹配",ResultJSON.TOKEN_ERROR,null);
        }else if (e instanceof  Exception){
            return new ResultJSON("无效token",ResultJSON.TOKEN_ERROR,null);
        }
        return new ResultJSON("未知token异常",ResultJSON.TOKEN_ERROR,null);
    }
}
