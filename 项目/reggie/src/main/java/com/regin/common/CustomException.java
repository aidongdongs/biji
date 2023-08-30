package com.regin.common;

//自定义运行时异常
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
