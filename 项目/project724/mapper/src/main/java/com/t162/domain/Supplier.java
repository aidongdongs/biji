package com.t162.domain;

import java.util.Date;

public class Supplier {
    private int id;
    private String supCode;
    private String supName;
    private String supDesc;
    private String supContact;
    private String supPhone;
    private String supAddress;
    private String supFax;
    private Integer createdUserId;
    private Date createdTime;
    private Integer updatedUserId;
    private Date updatedTime;

    public Supplier() {
    }

    public Supplier(int id, String supCode, String supName, String supDesc, String supContact, String supPhone, String supAddress, String supFax, Integer createdUserId, Date createdTime, Integer updatedUserId, Date updatedTime) {
        this.id = id;
        this.supCode = supCode;
        this.supName = supName;
        this.supDesc = supDesc;
        this.supContact = supContact;
        this.supPhone = supPhone;
        this.supAddress = supAddress;
        this.supFax = supFax;
        this.createdUserId = createdUserId;
        this.createdTime = createdTime;
        this.updatedUserId = updatedUserId;
        this.updatedTime = updatedTime;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return supCode
     */
    public String getSupCode() {
        return supCode;
    }

    /**
     * 设置
     * @param supCode
     */
    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    /**
     * 获取
     * @return supName
     */
    public String getSupName() {
        return supName;
    }

    /**
     * 设置
     * @param supName
     */
    public void setSupName(String supName) {
        this.supName = supName;
    }

    /**
     * 获取
     * @return supDesc
     */
    public String getSupDesc() {
        return supDesc;
    }

    /**
     * 设置
     * @param supDesc
     */
    public void setSupDesc(String supDesc) {
        this.supDesc = supDesc;
    }

    /**
     * 获取
     * @return supContact
     */
    public String getSupContact() {
        return supContact;
    }

    /**
     * 设置
     * @param supContact
     */
    public void setSupContact(String supContact) {
        this.supContact = supContact;
    }

    /**
     * 获取
     * @return supPhone
     */
    public String getSupPhone() {
        return supPhone;
    }

    /**
     * 设置
     * @param supPhone
     */
    public void setSupPhone(String supPhone) {
        this.supPhone = supPhone;
    }

    /**
     * 获取
     * @return supAddress
     */
    public String getSupAddress() {
        return supAddress;
    }

    /**
     * 设置
     * @param supAddress
     */
    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    /**
     * 获取
     * @return supFax
     */
    public String getSupFax() {
        return supFax;
    }

    /**
     * 设置
     * @param supFax
     */
    public void setSupFax(String supFax) {
        this.supFax = supFax;
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

    public String toString() {
        return "Supplier{id = " + id + ", supCode = " + supCode + ", supName = " + supName + ", supDesc = " + supDesc + ", supContact = " + supContact + ", supPhone = " + supPhone + ", supAddress = " + supAddress + ", supFax = " + supFax + ", createdUserId = " + createdUserId + ", createdTime = " + createdTime + ", updatedUserId = " + updatedUserId + ", updatedTime = " + updatedTime + "}";
    }
}
