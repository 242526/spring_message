package com.nd.spring_message.mapper;


import com.nd.spring_message.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 权限持久层
 */
@Repository
public interface IPermissionDao {
    /***
     * 根据roleId来查询权限
     * @param id
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findPermissionByRoleId(Integer id);

    /***
     * 查询所有权限
      * @return
     */
    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    /***
     * 保存权限
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission)throws Exception;
}
