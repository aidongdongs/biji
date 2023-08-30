package com.show.service.impl;

import com.show.mapper.SupplierMapper;
import com.show.pojo.Supplier;
import com.show.service.ISupplierService;
import com.show.utill.GetDateUtil;
import com.show.utill.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    public PageUtils pages(Integer currentPage, Integer pageSize ,String querySupCode, String querySupName) {
        //处理分页数字
        if (currentPage==1){
            currentPage=0;
        }
        if (currentPage>1){
            currentPage=currentPage-1;
        }
        currentPage = currentPage*pageSize;
        Integer count = supplierMapper.count(querySupCode,querySupName);
        PageUtils page= new PageUtils(currentPage,pageSize,Long.valueOf(count));
        List<Supplier> pages = supplierMapper.pages(currentPage, pageSize,querySupCode,querySupName);
        page.setList(pages);
        return page;
    }

    public boolean delete(String id) {
        Integer delete = supplierMapper.delete(id);
        if (delete==1){
            return true;
        }
        return false;
    }

    public Supplier queryById(String id) {
        return supplierMapper.queryById(id);
    }

    public Boolean update(Supplier sup,String userId) {
        //数据补全
//        sup.setUpdatedTime(new Date());
        sup.setUpdatedUserId(Long.valueOf(userId));

        Integer integer = supplierMapper.updateById(sup);
//        if (integer)
        return integer==1?true:false;
    }

    public Integer add(Supplier supplier,String uid) {
        supplier.setCreatedUserId(Long.valueOf(uid));
        supplier.setCreatedTime(new Date());
        supplier.setUpdatedUserId(Long.valueOf(uid));
        supplier.setUpdatedTime(new Date());
        return supplierMapper.add(supplier);
    }

    public List<Supplier> queryAll() {
        return supplierMapper.list();
    }
}
