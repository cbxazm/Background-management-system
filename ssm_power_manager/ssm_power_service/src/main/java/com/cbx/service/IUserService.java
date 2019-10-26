package com.cbx.service;

import com.cbx.domain.Role;
import com.cbx.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService  extends UserDetailsService{
    //查询用户
      List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(Integer id);

    List<Role> findOtherRoles(Integer userId);
   //给用户添加角色信息
    void addRoleToUser(Integer userId, Integer[] roleIds);
}
