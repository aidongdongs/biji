package com.t162.mapper.suppliermapper;

import com.t162.domain.Supplier;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMapper {
    @Delete("DELETE FROM t_supplier WHERE id=#{id}")
    int del(Integer id);

    public List<Supplier> selectSupplier(Supplier supplier);

    @Select("select  id, supCode, supName, supDesc, supContact, supPhone, supAddress, \n" +
            "        supFax, createdUserId, createdTime, updatedTime, updatedUserId\n" +
            "from t_supplier where id=#{id};")
    Supplier selectById(Integer id);

    int updateSupplier(Supplier supplier);

    int add(Supplier supplier);

    @Select("SELECT id ,supName FROM t_supplier")
    List<Supplier> selectSupplierName();
}

