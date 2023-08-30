package com.show.ex;

import com.show.controller.ResultJSON;

public class BaseException extends RuntimeException{
    private String  message;
    private Integer code;
    private Object data;


    public BaseException() {
        super();
    }

    public BaseException(String message,Integer code,Object data) {
        super(message);
        this.message=message;
        this.code=code;
        this.data=data;
    }
    public ResultJSON result(){
        return new ResultJSON(this.message,this.code,this.data);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message=message;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message=message;
    }
}
