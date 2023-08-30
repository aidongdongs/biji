package com.show.ex;

import com.show.controller.ResultJSON;

/**
 * 登录身份异常
 */
public class RoldException extends BaseException{
    public RoldException() {
        super();
    }

    public RoldException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public RoldException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoldException(Throwable cause) {
        super(cause);
    }

    protected RoldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
