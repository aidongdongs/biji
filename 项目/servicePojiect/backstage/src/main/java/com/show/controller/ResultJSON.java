package com.show.controller;

public class ResultJSON {

    //验证用户是否唯一   VerifyThatTheUserIsUnique_OK
    public static final Integer VerifyThatTheUserIsUnique_OK = 100;
    public static final Integer VerifyThatTheUserIsUnique_ERR = 101;
    //验证参数是否异常 PARAMETER_ERR
    public static final Integer   PARAMETER_ERR = 401;
    //验证码错误 VERIFICATION_CODE_MISTAKE_ERR
    public static final  Integer  VERIFICATION_CODE_MISTAKE_ERR =402;
    //注册身份错误
    public static final Integer REGISTER_ROLD_ERR=403;
    //数据库插入数据时出现异常
    public static final Integer INSERT_DATA_ERR = 404;



    //注册用户名称成功
    public static final Integer REGISTER_USER_NAME_SUCCESS = 405;
    //用户或者密码错误
    public static final Integer     USER_PASSWORD_NOT_FIND = 406;
    //账号被禁用 UserWasDisabledException
    public static final Integer USER_WAS_DISABLED=407;
    //登录成功
    public static final Integer LOGIN_SUCCESS = 408;
    //token异常
    public static final Integer TOKEN_ERROR = 409;
    //文件异常状态
    public static final Integer FIlE_UPLOAD_ERROR=410;
    //数据插入成功
    public static final  Integer INSERT_DATA_OK=411;

    //数据删除出现异常
    public static final Integer DELETE_DARA_OK = 412;
    //数据删除成功
    public static final Integer DELETE_DATA_ERR=422;
    //验证token成功
    public static final Integer VERIFICATION_TOKEN_OK= 423;

    //修改数据失败
    public static final Integer UPDATE_ERROR=424;
    public static final Integer UPDATE_OK=425;

    private String message;
    private Integer code;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultJSON() {
    }

    public ResultJSON(String message, Integer code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
}