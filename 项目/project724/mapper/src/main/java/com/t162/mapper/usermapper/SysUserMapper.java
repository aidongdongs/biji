package com.t162.mapper.usermapper;

import com.t162.domain.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/***
 *  日期： 2023/5/22 10:36
 *  作者： deng
 *  描述:
 */
@Repository
public interface SysUserMapper {

  SysUser login(SysUser sysUser);

  List<SysUser>selectAll(SysUser sysUser);
  @Select(" SELECT us.*,roleName as roleIdName, TimeStampDiff(year, birthday,now()) AS age " +
          "FROM t_sys_user us LEFT JOIN t_sys_role ro ON(us.roleId=ro.id) WHERE us.id=#{id} ")
  SysUser selectById(Integer id);

    int addAll(SysUser sysUser);
  @Select("select account from t_sys_user where account =#{account}")
  String selectByIdAccount(String account);
  @Delete("delete from t_sys_user where id=#{id}")
  int delById(Integer id);

  int updateUserById(SysUser sysUser);

  @Select("select password from t_sys_user where id=#{id} and password = #{oldPassword}")
  String selectByIdAndPassword(@Param("id") Integer id, @Param("oldPassword") String password);
  @Update("update t_sys_user set password = #{newPassword} where id=#{id};")

  int updatePassById(@Param("id") Integer id, @Param("newPassword") String password);

  @Select("SELECT COUNT(*) FROM t_sys_user WHERE roleId= #{id}")
  int selectByRoleId(Integer id);
}
