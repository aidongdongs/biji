package com.show.ex;

import com.show.controller.ResultJSON;

/**
 * 找不到用户名异常
 */
public class NotFindUserNameException extends BaseException{
    public NotFindUserNameException() {
        super();
    }

    public NotFindUserNameException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public NotFindUserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFindUserNameException(Throwable cause) {
        super(cause);
    }

    protected NotFindUserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
