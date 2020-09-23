package com.nd.spring_message.mapper;


import com.nd.spring_message.domain.Role;
import com.nd.spring_message.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    //javaType表示的是返回的类型是啥
    @Select("select * from users where username=#{username}")
    @Results(id ="userMap" ,value={
            @Result(id=true, property = "id" ,column="id"),
            @Result(property = "email" ,column="email"),
            @Result(property = "username" ,column="username"),
            @Result(property = "password" ,column="password"),
            @Result(property = "phoneNum" ,column="phoneNum"),
            @Result(property = "status" ,column="status"),
            @Result(property = "roles" ,column="id",javaType= List.class,many = @Many(select=("com.nd.spring_message.mapper.IRoleDao.fineRoleByUserId")))
    })
    UserInfo findByUserName(String username);

    /***
     * 查询所有用户
      * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
      * @param userInfo
     * @throws Exception
     */
    @Insert("insert into users(email,username,PASSWORD,phoneNum,STATUS) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo)throws Exception;

    /***
     * 根据用户id来查询详情信息
      * @param id
     * @return
     */

    @Select("select * from users where id=#{id}")
    @Results(value={
            @Result(id=true, property = "id" ,column="id"),
            @Result(property = "email" ,column="email"),
            @Result(property = "username" ,column="username"),
            @Result(property = "password" ,column="password"),
            @Result(property = "phoneNum" ,column="phoneNum"),
            @Result(property = "status" ,column="status"),
            @Result(property = "roles" ,column="id",javaType= List.class,many = @Many(select=("com.nd.dao.IRoleDao.fineRoleByUserId")))
    })
    UserInfo findById(Integer id) throws Exception;

    /***
     * 根据用户id来查询没有添加的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId} )")
    List<Role> findOtherRoles(Integer userId)throws Exception;

    /***
     * 根据用户id来添加角色
     * @param userId
     * @param roleId
     */
    @Insert("INSERT INTO users_role(userId,roleId) VALUES(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId)throws Exception;
}
