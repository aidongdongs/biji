package com.show.mapper;

import com.show.config.SpringConfig;
import com.show.pojo.User;
import com.show.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void pages(){
        System.out.println(userMapper.pages(1, 3, 1, null));
    }

    @Test
    public void testQueryById (){
        User user = userMapper.queryById("1");
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        UserVo user = new UserVo();
        user.setId("1");
        user.setCreatedTime("2002-2-3");
        userMapper.update(user);
    }

    @Test
    public void testDelete(){
        Integer delete = userMapper.delete("2");
        System.out.println(delete);
    }

    @Test
    public void testShowUser(){
        UserVo userVo = userMapper.showUser("1");
        System.out.println(userVo);
    }

    @Test
    public void testUserExit(){
        System.out.println(userMapper.userExit("a2dmin"));
    }

    @Test
    public void testAdd(){
        UserVo userVo = new UserVo();
        userVo.setAccount("sisgfg").setRealName("黄伟").setPassword("12345").setSex(1).setBirthday("200-2-3")
                .setPhone("12345678910").setAddress("adsjkhasjkd").setRoleId("1");

        Integer add = userMapper.add(userVo);
        System.out.println(add);
    }

    @Test
    public void testUpdatePassword(){
        System.out.println(userMapper.updatePassword("1111111", "1"));
    }




}
