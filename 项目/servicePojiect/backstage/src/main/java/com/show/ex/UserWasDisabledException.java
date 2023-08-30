package com.show.ex;

import com.show.controller.ResultJSON;

/**
 * 用户被禁用异常
 */
public class UserWasDisabledException  extends  BaseException{
    public UserWasDisabledException() {
        super();
    }

    public UserWasDisabledException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public UserWasDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWasDisabledException(Throwable cause) {
        super(cause);
    }

    protected UserWasDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
