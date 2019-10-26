package com.cbx.service;

import com.cbx.domain.Permission;

import java.util.List;

public interface IPermissionService {
        public List<Permission> findAll();

    void save(Permission permission);
}
