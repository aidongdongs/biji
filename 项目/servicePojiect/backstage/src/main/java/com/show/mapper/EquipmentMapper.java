package com.show.mapper;

import com.show.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface EquipmentMapper  {
    @Select("select * from equipment;")
    List<Equipment> getAll();
    @Update("\n" +
            "update equipment\n" +
            "set weight = weight+1\n" +
            "where id=#{id};")
    Integer addWeight(Integer id);
}
