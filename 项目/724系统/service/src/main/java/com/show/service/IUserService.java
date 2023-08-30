package com.show.service;

import com.show.pojo.User;
import com.show.vo.UserVo;
import com.show.utill.PageUtils;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    /**
     * 登录接口
     * @param account
     * @param password
     * @return
     */
    UserVo login(String account , String password);

    /**
     * 分页接口
     * @param currentPage
     * @param pageSize
     * @param queryRoleId
     * @param queryRealName
     * @return
     */
  PageUtils pages(Integer currentPage, Integer pageSize , Integer queryRoleId, String queryRealName);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryById(String id);

    Integer update(UserVo user, String uid);


    Integer delete(String id);


    UserVo showUser(String id);


    Integer userExit(String account);

    Integer add(UserVo userVo,String uid);

    String checkOldPwd (String password,String uid);

    Boolean updatePassword(String newPassword,String uid);
}
