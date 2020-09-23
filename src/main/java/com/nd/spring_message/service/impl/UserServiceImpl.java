package com.nd.spring_message.service.impl;


import com.nd.spring_message.domain.Role;
import com.nd.spring_message.domain.UserInfo;
import com.nd.spring_message.mapper.IUserDao;
import com.nd.spring_message.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserInfo findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void saveUser(UserInfo userInfo) throws Exception {
          userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer roleId) throws Exception {
        userDao.addRoleToUser(userId,roleId);
    }
}
