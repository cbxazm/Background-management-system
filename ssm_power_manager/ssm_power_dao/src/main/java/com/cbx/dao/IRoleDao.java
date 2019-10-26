package com.cbx.dao;

import com.cbx.domain.Permission;
import com.cbx.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作角色的dao
 */
public interface IRoleDao {
//    根据用户id查询所以的角色
@Select("select * from role where id in(select roleId from user_role where userId=#{userId})")
@Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "roleName",column = "roleName"),
        @Result(property = "roleDesc",column = "roleDesc"),
        @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cbx.dao.IPermissionDao.findPermissionByRoleId"))

})
    public List<Role> findRoleByUserId(Integer userId);
@Select("select * from role")
    List<Role> findAll();
@Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);
/**
     * 根据roleId查询出角色的信息
     * @return
     */
 @Select("select * from role where id=#{roleId}")
    Role findRoleByRolerId(Integer roleId);

    /**
     * 找出角色现在所不具备的权限
     * @param roleId
     * @return
     */
   @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(Integer roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 根据角色的id来查询出权限的信息
     * @param id
     * @return
     */
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id} )")
    List<Permission> findPermissionById(Integer id);
}
