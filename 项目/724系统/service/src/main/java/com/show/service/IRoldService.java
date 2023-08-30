package com.show.service;

import com.show.pojo.Role;

import java.util.List;

public interface IRoldService {
    List<Role> queryAll();

    Role queryById(String id);

    Boolean update(Role role,String uid);

    Integer queryByCodeCount(String code);

    Boolean add(String code,String roleName,String id);

    Integer delete(String id);
}
