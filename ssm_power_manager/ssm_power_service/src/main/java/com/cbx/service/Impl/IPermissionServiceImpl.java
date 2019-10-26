package com.cbx.service.Impl;

import com.cbx.dao.IPermissionDao;
import com.cbx.domain.Permission;
import com.cbx.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
  private IPermissionDao iPermissionDao;
    @Override
    public List<Permission> findAll() {
        return iPermissionDao.findAll() ;
    }

    @Override
    public void save(Permission permission) {
        iPermissionDao.save(permission);
    }
}
