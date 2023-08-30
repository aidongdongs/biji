package com.show.mapper;

import com.show.pojo.User;



public interface UserMapper {
    User getByUsername(String username);

    Integer addUser(User user);
}
