package com.nd.spring_message.service.impl;


import com.nd.spring_message.domain.Permission;
import com.nd.spring_message.domain.Role;
import com.nd.spring_message.mapper.IRoleDao;
import com.nd.spring_message.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) throws Exception{
        roleDao.saveRole(role);
    }

    @Override
    public Role findByRoleId(Integer roleId) throws Exception {
        return roleDao.findByRoleId(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(Integer roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception {
        for(Integer permissionId:permissionIds){
            roleDao.addPermissionToRole(permissionId,roleId);
        }
    }
}
