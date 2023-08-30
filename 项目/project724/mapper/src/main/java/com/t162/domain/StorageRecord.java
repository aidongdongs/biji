package com.t162.domain;
import java.util.Date;

public class StorageRecord {

	private Integer id;	//主键ID
	private String srCode;	//账单编码
	private String goodsName;	//商品名称
	private String goodsDesc;	//商品描述
	private String goodsUnit;	//商品单位
	private double goodsCount;	//商品数量
	private double totalAmount;	//商品总额
	private Integer payStatus;	//是否支付（1：未支付 2：已支付）
	private Integer createdUserId;	//创建者（userId）
	private Date createdTime;	//创建时间
	private Integer updatedUserId;	//更新者（userId）
	private Date updatedTime;	//更新时间
	private Integer supplierId;	//供应商ID
    private String supName;//供应商名称


	public Integer getId() {
    return id;
  }

	public void setId(Integer id) {
    this.id = id;
  }


    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSrCode() {
    return srCode;
  }

	public void setSrCode(String srCode) {
    this.srCode = srCode;
  }


	public String getGoodsName() {
    return goodsName;
  }

	public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }


	public String getGoodsDesc() {
    return goodsDesc;
  }

	public void setGoodsDesc(String goodsDesc) {
    this.goodsDesc = goodsDesc;
  }


	public String getGoodsUnit() {
    return goodsUnit;
  }

	public void setGoodsUnit(String goodsUnit) {
    this.goodsUnit = goodsUnit;
  }


	public double getGoodsCount() {
    return goodsCount;
  }

	public void setGoodsCount(double goodsCount) {
    this.goodsCount = goodsCount;
  }


	public double getTotalAmount() {
    return totalAmount;
  }

	public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }


	public Integer getPayStatus() {
    return payStatus;
  }

	public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }


	public Integer getCreatedUserId() {
    return createdUserId;
  }

	public void setCreatedUserId(Integer createdUserId) {
    this.createdUserId = createdUserId;
  }


	public Date getCreatedTime() {
    return createdTime;
  }

	public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }


	public Integer getUpdatedUserId() {
    return updatedUserId;
  }

	public void setUpdatedUserId(Integer updatedUserId) {
    this.updatedUserId = updatedUserId;
  }


	public Date getUpdatedTime() {
    return updatedTime;
  }

	public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }


	public Integer getSupplierId() {
    return supplierId;
  }

	public void setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
  }

}
