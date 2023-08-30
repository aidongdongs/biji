package com.show.mapper;

import com.show.pojo.Stock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StockMapper {

    @Select("select * from stock1")
    List<Stock>  list ();

    @Insert("insert into stock1 (id,username ) values (null,#{username});")
    Integer insert (Stock stock);
}
