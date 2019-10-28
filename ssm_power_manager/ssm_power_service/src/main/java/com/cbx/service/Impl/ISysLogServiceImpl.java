package com.cbx.service.Impl;

import com.cbx.dao.ISysLogDao;
import com.cbx.domain.SysLog;
import com.cbx.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
 private ISysLogDao iSysLogDao;
    @Override
    public void save(SysLog sysLog) {
            iSysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return iSysLogDao.findAll();
    }


}
