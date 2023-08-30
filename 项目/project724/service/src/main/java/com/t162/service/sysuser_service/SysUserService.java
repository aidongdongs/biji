package com.t162.service.sysuser_service;

import com.t162.domain.SysUser;

import java.util.List;

public interface SysUserService {
    SysUser login(SysUser sysUser);

    List<SysUser> selectAll(SysUser sysUser);
    SysUser selectById(Integer id);

    int addAll(SysUser sysUser);

    String selectByIdAccount(String account);

    int delById(Integer id);

    int updateUserById(SysUser sysUser);

    String selectByIdAndPassword(Integer id,String password);

    int updatePassById(Integer id,String password);
    int selectByRoleId(Integer id);
}

