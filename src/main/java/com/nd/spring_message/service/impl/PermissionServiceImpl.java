package com.nd.spring_message.service.impl;


import com.nd.spring_message.domain.Permission;
import com.nd.spring_message.mapper.IPermissionDao;
import com.nd.spring_message.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception{
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) throws Exception {
        permissionDao.savePermission(permission);
    }
}
