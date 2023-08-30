package com.show.ex;

import com.show.controller.ResultJSON;

/**
 * 插入数据失败异常
 */
public class InsertDateException extends BaseException {
    public InsertDateException() {
        super();
    }

    public InsertDateException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public InsertDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertDateException(Throwable cause) {
        super(cause);
    }

    protected InsertDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
