package com.cbx.service;

import com.cbx.domain.Role;

import java.util.List;

/**
 * 角色的业务层
 */
public interface IRoleService {
         public  List<Role> findAll();

    void save(Role role);
}
