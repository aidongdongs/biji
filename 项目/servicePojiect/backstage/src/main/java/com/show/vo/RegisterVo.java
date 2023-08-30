package com.show.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前端传递过来进行注册的数据对象
 */
@Data
@Accessors(chain = true)
public class RegisterVo {
    /**
     * username:"",
     *                 password:"",
     *                 confirmPassword:"",
     *                 phone:"",
     *                 rold:"1",
     *                 VerificationCode:"",
     *                 md5Password:""
     */
    private String username;
    private String phone;
    private String rold;
    private String verificationCode;
    private String md5Password;

}
