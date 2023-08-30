package com.show.vo;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前端传递到过来的用户登录数据
 */
@Data
@Accessors(chain = true)
public class LoginVo {
    /**
     *   login:{
     *                 user:"",
     *                 password:"",
     *                 rold:"1",
     *                 VerificationCode:""
     *             }
     */


    private String user;
    private String password;
    private String rold;
    private String verificationCode;

}
