package com.nd.spring_message.service;


import com.nd.spring_message.domain.Permission;
import com.nd.spring_message.domain.Role;

import java.util.List;

/***
 * 角色业务层
 */
public interface IRoleService {

    /***
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /***
     * 保存角色
      * @param role
     */
    void saveRole(Role role)throws Exception;

    /**
     * 根据roleId来查询角色
     * @param roleId
     * @return
     * @throws Exception
     */
    Role findByRoleId(Integer roleId)throws Exception;

    /***
     * 根据roleId来查询Permissions
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Permission> findOtherPermission(Integer roleId)throws Exception;

    /***
     * 根据role添加没有的权限
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception;
}
