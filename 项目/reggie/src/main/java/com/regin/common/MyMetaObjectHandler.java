package com.regin.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 自定义原数据对象处理
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    HttpServletRequest httpServletRequest;


    /**
     * 插入数据自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
            metaObject.setValue("createTime",LocalDateTime.now());
            metaObject.setValue("updateTime",LocalDateTime.now());

       if (httpServletRequest.getSession().getAttribute("employee")!=null){
           metaObject.setValue("createUser",httpServletRequest.getSession().getAttribute("employee"));
           metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("employee"));
       }else if (httpServletRequest.getSession().getAttribute("user")!=null){
           metaObject.setValue("createUser",httpServletRequest.getSession().getAttribute("user"));
           metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("user"));
       }
    }

    /**
     * 更新操作的时候填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",LocalDateTime.now());
        if (httpServletRequest.getSession().getAttribute("employee")!=null){
            metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("employee"));
        }else if (httpServletRequest.getSession().getAttribute("user")!=null){
            metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("user"));
        }
    }
}
