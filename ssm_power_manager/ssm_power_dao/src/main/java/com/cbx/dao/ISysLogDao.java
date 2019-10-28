package com.cbx.dao;

import com.cbx.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISysLogDao {
    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
     void save(SysLog sysLog);
  @Select("select * from sysLog")
    List<SysLog> findAll();
}
