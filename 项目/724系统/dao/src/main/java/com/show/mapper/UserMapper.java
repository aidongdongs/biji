package com.show.mapper;

import com.show.pojo.User;
import com.show.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from t_sys_user where account=#{account} and password=#{password}")
    UserVo login(@Param("account") String account , @Param("password") String password);

    List<UserVo> pages(
            @Param("currentPage") Integer currentPage,
            @Param("pageSize") Integer pageSize ,
            @Param("queryRoleId") Integer queryRoleId,
            @Param("queryRealName") String queryRealName);

    Integer count(@Param("queryRoleId") Integer queryRoleId, @Param("queryRealName") String queryRealName);

    /**
     * 查询全部
     * @return
     */
    List<UserVo> queryAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User queryById(String id);

    /**
     * 修改
     * @param user
     * @return
     */
    Integer update(UserVo user);

    Integer delete(String id);

    UserVo showUser(String id);

    Integer userExit(String account);

    Integer add(UserVo userVo);

    User checkOldPwd(@Param("password") String password ,@Param("uid") String uid);

    Integer updatePassword(@Param("passwrod") String passwrod,
                           @Param("uid") String uid);


}
