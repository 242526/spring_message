package com.nd.spring_message.service;

import com.nd.spring_message.domain.Role;
import com.nd.spring_message.domain.UserInfo;

import java.util.List;

public interface IUserService {
    //根据username查询
    UserInfo findByUserName(String username);

    List<UserInfo> findAll() throws Exception;

    void saveUser(UserInfo userInfo)throws Exception;


    UserInfo findById(Integer id) throws Exception;

    List<Role> findOtherRoles(Integer userId)throws Exception;

    void addRoleToUser(Integer userId, Integer roleId)throws Exception;

}
