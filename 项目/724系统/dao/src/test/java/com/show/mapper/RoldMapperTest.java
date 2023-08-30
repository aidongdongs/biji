package com.show.mapper;

import com.show.config.SpringConfig;
import com.show.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class RoldMapperTest {

    @Autowired
    private RoleMapper roldMapper;
    @Test
    public void testQueryAll(){
        System.out.println(roldMapper.queryAll());
    }


    @Test
    public void  testQueryById(){
        System.out.println(roldMapper.queryById("1"));
    }

    @Test
    public void testUpdate(){

        Role role = new Role();
        role.setId(1).setRoleName("系统管理员1");

        System.out.println(roldMapper.update(role));
    }

    @Test
    public void testQueryByCodeCount(){
        System.out.println(roldMapper.queryByCodeCount("SMBMS_ADMIN"));
    }

    @Test
    public void testDelete(){
        System.out.println(roldMapper.delete("7"));
    }

    @Test
    public void testAdd(){
        Role role = new Role();
        role.setCode("asdas").setRoleName("asdas").setCreatedUserId("1").
                setCreatedTime(new Date()).setUpdatedUserId(1).setUpdatedTime(new Date());
        System.out.println(roldMapper.add(role));
    }
}
