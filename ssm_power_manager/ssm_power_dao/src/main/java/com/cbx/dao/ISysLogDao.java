package com.cbx.dao;

import com.cbx.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogDao {
    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
     void save(SysLog sysLog);
}
