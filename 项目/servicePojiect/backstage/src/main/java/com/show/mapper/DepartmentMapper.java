package com.show.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.show.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    @Select("select * from department;")
    List<Department> getAll();

    @Update("\n" +
            "update department\n" +
            "set weight = weight+1\n" +
            "where id=#{id};")
    Integer addWeight(Integer id);
}
