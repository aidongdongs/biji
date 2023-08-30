package com.show.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private String id;
    private String account;
    private String realName;
    private String password;
    private Integer sex;
    private Date birthday;
    private String phone;
    private String address;
    private String roleId;
    private String createdUserId;
    private Date createdTime;
    private Integer updatedUserId;
    private Date updatedTime;
}
