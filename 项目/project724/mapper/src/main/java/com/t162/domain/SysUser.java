package com.t162.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


public class SysUser {
    private Integer id;//编号
    private String account;//账号
    private String realName;//真实姓名
    private String password;//密码
    private Integer sex;//性别（0为女  1为男）
    private Date birthday;//出生日期
    private String phone;//手机号码
    private String address;//地址
    private Integer roleId;//角色编号（1为管理员  2为经理  3为普通用户）

    private Integer createdUserId;//创建者ID
    private Date createdTime;//创建时间
    private Integer updatedUserId;//更新这ID
    private Date updatedTime;//更新时间
    private String roleIdName;
    private Integer age;


    public SysUser() {
    }

    public SysUser(Integer id, String account, String realName, String password, Integer sex, Date birthday, String phone, String address, Integer roleId, Integer createdUserId, Date createdTime, Integer updatedUserId, Date updatedTime, String roleIdName, Integer age) {
        this.id = id;
        this.account = account;
        this.realName = realName;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.createdUserId = createdUserId;
        this.createdTime = createdTime;
        this.updatedUserId = updatedUserId;
        this.updatedTime = updatedTime;
        this.roleIdName = roleIdName;
        this.age = age;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取
     * @return createdUserId
     */
    public Integer getCreatedUserId() {
        return createdUserId;
    }

    /**
     * 设置
     * @param createdUserId
     */
    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    /**
     * 获取
     * @return createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取
     * @return updatedUserId
     */
    public Integer getUpdatedUserId() {
        return updatedUserId;
    }

    /**
     * 设置
     * @param updatedUserId
     */
    public void setUpdatedUserId(Integer updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    /**
     * 获取
     * @return updatedTime
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置
     * @param updatedTime
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取
     * @return roleIdName
     */
    public String getRoleIdName() {
        return roleIdName;
    }

    /**
     * 设置
     * @param roleIdName
     */
    public void setRoleIdName(String roleIdName) {
        this.roleIdName = roleIdName;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "SysUser{id = " + id + ", account = " + account + ", realName = " + realName + ", password = " + password + ", sex = " + sex + ", birthday = " + birthday + ", phone = " + phone + ", address = " + address + ", roleId = " + roleId + ", createdUserId = " + createdUserId + ", createdTime = " + createdTime + ", updatedUserId = " + updatedUserId + ", updatedTime = " + updatedTime + ", roleIdName = " + roleIdName + ", age = " + age + "}";
    }
}
