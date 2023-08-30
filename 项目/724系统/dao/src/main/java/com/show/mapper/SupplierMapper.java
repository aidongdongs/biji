package com.show.mapper;

import com.show.pojo.Supplier;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SupplierMapper {
    /**
     * 查询商品总数，用于分页拆查询
     * @return
     */

    Integer count(@Param("querySupCode") String querySupCode, @Param("querySupName") String querySupName);

    /**
     * 分页查询
     * @param currentPage 页数
     * @param pageSize 每页大小
     * @return
     */

    List<Supplier> pages(
            @Param("currentPage") Integer currentPage
            ,@Param("pageSize") Integer pageSize
            ,@Param("querySupCode") String querySupCode
            ,@Param("querySupName") String querySupName
    );

    /**
     * 删除商品
     * @param id
     * @return
     */
   @Delete("delete\n" +
           "from t_supplier\n" +
           "where id = #{id};")
    Integer delete (String id);

    /**
     * 查询所有商品
     * @return
     */
   List<Supplier> list ();

    /**
     * 根据id查询商户
     * @param id
     * @return
     */
   Supplier queryById(String id);

   Integer updateById(Supplier supplier);


   Integer add(Supplier supplier);


}
