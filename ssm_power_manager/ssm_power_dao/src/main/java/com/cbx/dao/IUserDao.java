package com.cbx.dao;

import com.cbx.domain.Role;
import com.cbx.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
//    需要把用户的角色信息也获取到
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column ="id",javaType = java.util.List.class,many = @Many(select ="com.cbx.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUserName(String username);
@Select("select * from users")
    List<UserInfo> findAll();
 @Insert("insert into users (email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status}) ")
    void save(UserInfo userInfo);
 //这个涉及到复杂的多表关联的操作
  @Select("select * from users where id=#{id}")
   @Results({
           @Result(id = true,property = "id",column = "id"),
           @Result(property = "username",column = "username"),
           @Result(property = "email",column = "email"),
           @Result(property = "password",column = "password"),
           @Result(property = "email",column = "email"),
           @Result(property = "phoneNum",column = "phoneNum"),
           @Result(property = "status",column = "status"),
           @Result(property = "roles" ,column="id",javaType = java.util.List.class,many = @Many(select = "com.cbx.dao.IRoleDao.findRoleByUserId"))
   })
    UserInfo findById(Integer id);

    /**
     * 用于查询该user可以添加的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from user_role where userId=#{userId})")
    List<Role> findOtherRoles(Integer userId);

    /**
     * 真正给用户添加角色信息
     * @param userId
     * @param roleId
     */
    @Insert("insert into user_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
}
