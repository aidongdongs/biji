package com.t162.domain;


import java.util.Date;

public class SysRole {

  private long id;//自增Id
  private String code;//编码
  private String roleName;//角色名称
  private long createdUserId;//创建者ID
  private Date createdTime;//创建时间
  private long updatedUserId;//更新者Id
  private Date updatedTime;//更新时间


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public long getCreatedUserId() {
    return createdUserId;
  }

  public void setCreatedUserId(long createdUserId) {
    this.createdUserId = createdUserId;
  }


  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }


  public long getUpdatedUserId() {
    return updatedUserId;
  }

  public void setUpdatedUserId(long updatedUserId) {
    this.updatedUserId = updatedUserId;
  }


  public Date getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

}
