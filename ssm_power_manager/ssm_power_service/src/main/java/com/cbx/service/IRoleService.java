package com.cbx.service;

import com.cbx.domain.Permission;
import com.cbx.domain.Role;

import java.util.List;

/**
 * 角色的业务层
 */
public interface IRoleService {
         public  List<Role> findAll();

    void save(Role role);

    Role findById(Integer roleId);

    /**
     * 根据roleId获取为添加的权限信息
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);

    List<Permission> findPermissionById(Integer id);
}
