package com.t162.service.role_service.impl;

import com.t162.domain.SysRole;
import com.t162.mapper.rolemapper.RoleMapper;
import com.t162.service.role_service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<SysRole> selectRoleName() {
        return roleMapper.selectRoleName();
    }

    @Override
    public int addUser(SysRole sysRole) {
        return roleMapper.addUser(sysRole);
    }

    @Override
    public int delUser(Integer id) {
        return roleMapper.delUser(id);
    }

    @Override
    public int updateRole(SysRole sysRole) {
        return roleMapper.updateRole(sysRole);
    }

    @Override
    public String findCode(String code) {
        return roleMapper.findCode(code);
    }

    @Override
    public SysRole selectSysRoleById(Integer id) {
        return roleMapper.selectSysRoleById(id);
    }


}
