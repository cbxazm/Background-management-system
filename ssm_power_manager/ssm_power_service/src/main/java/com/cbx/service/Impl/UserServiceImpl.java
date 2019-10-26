package com.cbx.service.Impl;

import com.cbx.dao.IUserDao;
import com.cbx.domain.Role;
import com.cbx.domain.UserInfo;
import com.cbx.service.IUserService;
import com.cbx.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo= userDao.findByUserName(username);
//        User类实现了UserDetails接口
        //处理自己的用户对象封装成为UserDetails
//        User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        //使用新的构造函数来完成
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==1?true:false,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
    //返回一个装角色描述的List集合
     public  List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role:roles){
                list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
     }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        //对密码进行加密（可以使用bean，或者调用工具类的加密方法）
        userInfo.setPassword( BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
           userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) {

        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for (Integer roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);

        }
    }
}
