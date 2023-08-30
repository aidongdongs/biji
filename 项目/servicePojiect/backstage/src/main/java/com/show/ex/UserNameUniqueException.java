package com.show.ex;

import com.show.controller.ResultJSON;

/**
 * 用户名不唯一异常
 */
public class UserNameUniqueException extends BaseException{
    public UserNameUniqueException() {
        super();
    }

    public UserNameUniqueException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public UserNameUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameUniqueException(Throwable cause) {
        super(cause);
    }

    protected UserNameUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
