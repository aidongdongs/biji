package com.t162.service.supplier_service.impl;

import com.t162.domain.Supplier;
import com.t162.mapper.suppliermapper.SupplierMapper;
import com.t162.service.supplier_service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Override
    public List<Supplier> selectSupplier(Supplier supplier) {
        return supplierMapper.selectSupplier(supplier);
    }

    @Override
    public int del(Integer id) {
        return supplierMapper.del(id);
    }

    @Override
    public Supplier selectById(Integer id) {
        return supplierMapper.selectById(id);
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierMapper.updateSupplier(supplier);
    }

    @Override
    public int add(Supplier supplier) {
        return supplierMapper.add(supplier);
    }

    @Override
    public List<Supplier> selectSupplierName() {
        return supplierMapper.selectSupplierName();
    }


}
