package com.show.mapper;

import com.show.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import java.util.List;


public interface OrderMapper {

    List<Order>  list ();
    
    @Insert("insert into  order1 (id,username) values (null,username);")
    Integer insert(Order order);
}
