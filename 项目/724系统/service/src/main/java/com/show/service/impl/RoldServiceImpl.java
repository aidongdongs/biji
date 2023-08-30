package com.show.service.impl;

import com.show.mapper.RoleMapper;
import com.show.pojo.Role;
import com.show.service.IRoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoldServiceImpl implements IRoldService {


    @Autowired
    private RoleMapper roldMapper;

    public List<Role> queryAll() {
        return roldMapper.queryAll();
    }

    public Role queryById(String id) {
        return roldMapper.queryById(id);
    }

    public Boolean update(Role role,String uid) {
        role.setCreatedUserId(uid);
        role.setUpdatedUserId(Integer.valueOf(uid));
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        if (roldMapper.update(role)==1){
            return true;
        }
            return false;
    }

    public Integer queryByCodeCount(String code) {
        return roldMapper.queryByCodeCount(code);
    }

    public Boolean add(String code, String roleName,String id) {
        Role role = new Role();
        role.setCode(code).setRoleName(roleName).setCreatedUserId(id).
                setUpdatedUserId(Integer.valueOf(id))
                .setCreatedTime(new Date()).setUpdatedTime(new Date());
        Integer add = roldMapper.add(role);
        if (add==1){
            return true;
        }else {
            return false;
        }
    }

    public Integer delete(String id) {
       return roldMapper.delete(id);
    }
}
