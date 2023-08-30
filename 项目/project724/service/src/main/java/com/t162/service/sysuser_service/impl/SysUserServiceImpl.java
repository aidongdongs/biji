package com.t162.service.sysuser_service.impl;

import com.t162.domain.SysUser;
import com.t162.mapper.usermapper.SysUserMapper;
import com.t162.service.sysuser_service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public String selectByIdAndPassword(Integer id,String password) {
        return userMapper.selectByIdAndPassword(id, password);
    }

    @Override
    public int selectByRoleId(Integer id) {
        return userMapper.selectByRoleId(id);
    }

    @Override
    public int updatePassById(Integer id, String password) {
        return userMapper.updatePassById(id,password);
    }

    @Override
    public int updateUserById(SysUser sysUser) {
        return userMapper.updateUserById(sysUser);
    }

    @Override
    public int delById(Integer id) {
        return userMapper.delById(id);
    }

    @Override
    public SysUser login(SysUser sysUser) {
        return userMapper.login(sysUser);
    }

    @Override
    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public String selectByIdAccount(String account) {
        return userMapper.selectByIdAccount(account);
    }

    @Override
    public int addAll(SysUser sysUser) {
        return userMapper.addAll(sysUser);
    }

    @Override
    public List<SysUser> selectAll(SysUser sysUser) {
        return userMapper.selectAll(sysUser);
    }
}
