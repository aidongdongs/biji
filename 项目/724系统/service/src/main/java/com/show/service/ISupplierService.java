package com.show.service;

import com.show.pojo.Supplier;
import com.show.utill.PageUtils;

import java.util.List;

public interface ISupplierService {
    /**
     * 分页查询
     * @param currentPage 页数
     * @param pageSize  每页大小
     * @param querySupCode 商户编码
     * @param querySupName 商户名称
     * @return
     */
    PageUtils pages(Integer currentPage, Integer pageSize,String querySupCode, String querySupName);

    /**
     * 删除商户
     * @param id
     * @return
     */
    boolean delete (String id);

    /**
     * 根据id查询商户
     * @param id
     * @return
     */
    Supplier queryById (String id);

    /**
     * 根据id修改商户
     * @param sup
     * @return
     */
    Boolean update (Supplier sup,String id);

    /**
     * 增加商品
     * @param supplier
     * @return
     */
    Integer add (Supplier supplier,String uid);


    /**
     * 查询全部
     * @return
     */
    List<Supplier> queryAll();
}
