package com.cbx.service.Impl;

import com.cbx.dao.IRoleDao;
import com.cbx.domain.Permission;
import com.cbx.domain.Role;
import com.cbx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
          iRoleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return iRoleDao.findRoleByRolerId(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(Integer roleId) {
        return iRoleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for(Integer permissionId:permissionIds){
                     iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findPermissionById(Integer id) {
        return iRoleDao.findPermissionById(id);
    }
}
