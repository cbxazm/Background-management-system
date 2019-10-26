package com.cbx.dao;

import com.cbx.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限资源的持久层
 */
public interface IPermissionDao {
//    根据id查询角色所有的资源权限
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(Integer id);
@Select("select * from permission")
    List<Permission> findAll();
@Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
