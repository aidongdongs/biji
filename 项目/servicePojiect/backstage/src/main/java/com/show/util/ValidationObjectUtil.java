package com.show.util;

import com.show.controller.ResultJSON;
import com.show.ex.ParameterNullException;
import com.show.ex.VerificationPhoneException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
    验证对对象工具类
 */
@Slf4j
public class ValidationObjectUtil {
    public static  void ValidationObjectIsNull(Object object)  {

        if(object==null){
            throw new ParameterNullException("您传递的参数是空的", ResultJSON.PARAMETER_ERR,null);
        }
        Class<?> objectClass = object.getClass();
        Field[] declaredFields = objectClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object o = null;
            try {
                o = declaredField.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (o==null||o==""){
                log.error("接口使用对象接收数据有null值，抛出异常");
                //如果等于null直接抛出异常
                throw new  ParameterNullException("你传递的参数不完整",ResultJSON.PARAMETER_ERR,null);
            }
        }

    }

    public static void ValidationPhone(String phone){
        String phoneRegular = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
        if(!phone.matches(phoneRegular)){
            log.error("你的手机号码不正确");
            throw new VerificationPhoneException("手机号格式不正确",ResultJSON.PARAMETER_ERR,null);
        }
    }
}
