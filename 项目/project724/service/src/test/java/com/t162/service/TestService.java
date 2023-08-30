package com.t162.service;

import com.t162.domain.StorageRecord;
import com.t162.domain.Supplier;
import com.t162.domain.SysUser;
import com.t162.mapper.suppliermapper.SupplierMapper;
import com.t162.service.storageRecord_service.StorageRecordService;
import com.t162.service.supplier_service.SupplierService;
import com.t162.service.sysuser_service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext-service.xml")
public class TestService {
    @Autowired
    private SysUserService service;
    @Autowired
    private SupplierService supplier;
    @Autowired
    private StorageRecordService storageRecordService;

    @Test
    public void testUpdate(){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            String dateBirthday="2001-12-11";
            Date date=null;
        try {
            date = ft.parse(dateBirthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SysUser sysUser=new SysUser();
        sysUser.setId(17);
        sysUser.setRealName("sung");
//        sysUser.setBirthday(date);
        int i = service.updateUserById(sysUser);
        System.out.println(i);
    }

    @Test
    public void testUser(){
        SysUser sysUser=new SysUser();
        sysUser.setRoleId(1);
        List<SysUser> sysUsers = service.selectAll(sysUser);
        System.out.println(sysUsers.toString());
    }
    @Test
    public void testByaccout(){
        String admin = service.selectByIdAccount("ad1min");
        System.out.println(admin);
    }

    @Test
    public void testLogin(){
        SysUser user =new SysUser();
        user.setAccount("admin");
        user.setPassword("1111111");
        SysUser login = service.login(user);
        System.out.println(login);
    }
    @Test
    public void testSelectSupplier(){
        Supplier s=new Supplier();
        List<Supplier> suppliers = supplier.selectSupplier(s);
        System.out.println(suppliers);
    }
    @Test
    public void  testSelectAllSto(){
        StorageRecord sto=new StorageRecord();
        sto.setGoodsName("米");
        List<StorageRecord> storageRecords = storageRecordService.selectAllStorageRecord(sto);
        System.out.println(storageRecords.toString());
    }
}
