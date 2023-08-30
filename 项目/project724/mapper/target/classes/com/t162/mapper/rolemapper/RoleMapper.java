package com.t162.mapper.rolemapper;

import com.t162.domain.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    @Select("SELECT * FROM t_sys_role")
    List<SysRole>selectRoleName();

    @Select("select code from t_sys_role where code=#{code}")
    String findCode(String code);

    @Insert("insert into t_sys_role (code, roleName, createdUserId," +
            " createdTime) values (#{code},#{roleName},#{createdUserId},now())")
    int addUser(SysRole sysRole);

    @Delete("delete from t_sys_role where id=#{id};")
    int delUser(Integer id);

    int updateRole(SysRole sysRole);
    @Select("SELECT * FROM t_sys_role where id=#{id}")
    SysRole selectSysRoleById(Integer id);
}
