package com.show.mapper;

import com.show.pojo.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> queryAll();

    Role queryById(String id);

    Integer update (Role role);

    Integer queryByCodeCount(String code);

    Integer add(Role role);

    Integer delete(String id);
}
