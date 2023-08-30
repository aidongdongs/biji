package com.show.service.impl;


import com.show.mapper.UserMapper;
import com.show.pojo.User;
import com.show.utill.GetDateUtil;
import com.show.vo.UserVo;
import com.show.service.IUserService;
import com.show.utill.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;




    public UserVo login(String account, String password) {
       return userMapper.login(account,password);
    }

    public PageUtils pages(Integer currentPage, Integer pageSize, Integer queryRoleId, String queryRealName) {
       if (currentPage==1){
           currentPage=0;
       }
        if (currentPage>1){
            currentPage=currentPage-1;
        }
        currentPage = currentPage*pageSize;

        Integer count = userMapper.count(queryRoleId, queryRealName);
        PageUtils page= new PageUtils(currentPage,pageSize,Long.valueOf(count));
        List<UserVo> pages = userMapper.pages(currentPage, pageSize,queryRoleId,queryRealName);
        page.setList(pages);
        return page;
    }

    public User queryById(String id) {
        return userMapper.queryById(id);
    }

    public Integer update(UserVo user, String uid) {

        user.setUpdatedTime(GetDateUtil.getDate(new Date()));

        return userMapper.update(user);
    }

    public Integer delete(String id) {
        return userMapper.delete(id);
    }

    public UserVo showUser(String id) {
        return userMapper.showUser(id);
    }

    public Integer userExit(String account) {
        return userMapper.userExit(account);
    }

    public Integer add(UserVo userVo ,String uid) {
        userVo.setCreatedUserId(uid);
        userVo.setCreatedTime(GetDateUtil.getDate(new Date()));
        userVo.setUpdatedUserId(Integer.valueOf(uid));
        userVo.setUpdatedTime(GetDateUtil.getDate(new Date()));
        return userMapper.add(userVo);
    }

    public String checkOldPwd(String password, String uid) {
        User user = userMapper.checkOldPwd(password, uid);

        if (user!=null){
            if (user.getPassword().equals(password)){
                return "true";
            }
        }
        return "false";
    }

    public Boolean updatePassword(String newPassword, String uid) {
        if ( userMapper.updatePassword(newPassword,uid)==1){
            return true;
        }else {
            return false;
        }

    }


}
