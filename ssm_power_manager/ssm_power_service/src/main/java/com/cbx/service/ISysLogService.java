package com.cbx.service;

import com.cbx.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll(int page,int size);
}
