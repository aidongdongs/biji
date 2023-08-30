package com.t162.mapper;

import com.t162.domain.Supplier;
import com.t162.domain.SysUser;
import com.t162.mapper.suppliermapper.SupplierMapper;
import com.t162.mapper.usermapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext-dao.xml")
public class TestMapper {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Test
    public void loginTest(){
        SysUser user =new SysUser();
        user.setAccount("admin");
        user.setPassword("1111111");
        SysUser login = sysUserMapper.login(user);
        System.out.println(login);
    }
    @Test
    public void selectSupplierTest(){
        Supplier supplier=new Supplier();
        List<Supplier> suppliers = supplierMapper.selectSupplier(supplier);
        System.out.println(suppliers);
    }
}
