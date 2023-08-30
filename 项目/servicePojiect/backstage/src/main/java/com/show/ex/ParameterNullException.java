package com.show.ex;

/**
 * 当前端传递的参数为空的时候抛出异常
 */
public class ParameterNullException extends  BaseException {
    public ParameterNullException() {
        super();
    }

    public ParameterNullException(String message,Integer code,Object object) {
        super(message,code,object);
    }

    public ParameterNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterNullException(Throwable cause) {
        super(cause);
    }

    protected ParameterNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
