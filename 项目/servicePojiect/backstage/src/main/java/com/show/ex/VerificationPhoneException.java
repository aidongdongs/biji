package com.show.ex;

import com.show.controller.ResultJSON;

/**
 * 手机号码错误异常
 */
public class VerificationPhoneException extends BaseException{
    public VerificationPhoneException() {
        super();
    }

    public VerificationPhoneException(String message, Integer code, Object data) {
        super(message, code, data);
    }

    @Override
    public ResultJSON result() {
        return super.result();
    }

    public VerificationPhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationPhoneException(Throwable cause) {
        super(cause);
    }

    protected VerificationPhoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
