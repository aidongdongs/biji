package com.t162.domain;

public class Result {
    private String delResult;
    private Integer exist;


    public Result() {
    }
    public Result(String delResult) {
        this.delResult=delResult;
    }
    public Result(Integer exist) {
        this.exist=exist;
    }
    public Result(String delResult, Integer exist) {
        this.delResult = delResult;
        this.exist = exist;
    }

    /**
     * 获取
     * @return delResult
     */
    public String getDelResult() {
        return delResult;
    }

    /**
     * 设置
     * @param delResult
     */
    public void setDelResult(String delResult) {
        this.delResult = delResult;
    }

    /**
     * 获取
     * @return exist
     */
    public Integer getExist() {
        return exist;
    }

    /**
     * 设置
     * @param exist
     */
    public void setExist(Integer exist) {
        this.exist = exist;
    }

    public String toString() {
        return "Result{delResult = " + delResult + ", exist = " + exist + "}";
    }
}
