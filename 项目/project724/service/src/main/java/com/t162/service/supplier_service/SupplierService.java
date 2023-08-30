package com.t162.service.supplier_service;

import com.t162.domain.Supplier;

import java.util.List;

public interface SupplierService {
    public List<Supplier> selectSupplier(Supplier supplier);

    int del(Integer id);

    Supplier selectById(Integer id);

    int updateSupplier(Supplier supplier);

    int add(Supplier supplier);

    List<Supplier> selectSupplierName();


}
