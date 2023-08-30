package com.t162.service.role_service;

import com.t162.domain.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole> selectRoleName();
    int addUser(SysRole sysRole);
    int delUser(Integer id);
    int updateRole(SysRole sysRole);
    String findCode(String code);
    SysRole selectSysRoleById(Integer id);
}
