package com.cbx.service.Impl;

import com.cbx.dao.ISysLogDao;
import com.cbx.domain.SysLog;
import com.cbx.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
 private ISysLogDao iSysLogDao;
    @Override
    public void save(SysLog sysLog) {
            iSysLogDao.save(sysLog);
    }
}
