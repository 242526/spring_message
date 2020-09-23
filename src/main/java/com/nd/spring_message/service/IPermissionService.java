package com.nd.spring_message.service;



import com.nd.spring_message.domain.Permission;

import java.util.List;

/***
 * 权限业务层--接口
 */
public interface IPermissionService {
    /***
     * 查询所有权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;

    /***
     * 保存权限
     * @param permission
     */
    void savePermission(Permission permission)throws Exception;
}
