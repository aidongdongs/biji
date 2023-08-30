package com.show.ex;

import com.show.controller.ResultJSON;

public class UpdateException extends BaseException{
    public UpdateException() {
        super();
    }

    public UpdateException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
