package com.nd.spring_message.mapper;


import com.nd.spring_message.domain.Permission;
import com.nd.spring_message.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 角色表查询
 */
@Repository
public interface IRoleDao {
    /***
     * 根据用户的id来查询角色
     * @return
     */
    @Select(" SELECT * FROM role WHERE id IN(SELECT roleId FROM users_role WHERE userId=#{userId})")
    @Results({
            @Result(id=true,property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,many = @Many(select = "com.nd.spring_message.mapper.IPermissionDao.findPermissionByRoleId")),
    })
    public List<Role> fineRoleByUserId(Integer userId);

    /***
     * 查询所有角色
      * @return
     * @throws Exception
     */
    @Select("SELECT * FROM role")
    public List<Role> findAll()throws Exception;

    /***
     * 保存角色成功
     * @param role
     * @throws Exception
     */
    @Insert("insert into role(roleName,roleDesc) value(#{roleName},#{roleDesc})")
    void saveRole(Role role)throws Exception;

    /***
     * 根据RoleId来查询角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id=#{roleId}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id" ,javaType = List.class, many = @Many(select = "com.nd.dao.IPermissionDao.findPermissionByRoleId")),

    })
    Role findByRoleId(Integer roleId)throws Exception;

    /***
     * 根据RoleId来查询没有的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("SELECT * FROM permission WHERE id NOT IN(SELECT  permissionId FROM role_permission WHERE roleId=#{roleId})")
    List<Permission> findOtherPermission(Integer roleId)throws Exception;

    /***
     * role添加没有出现的权限
     * @param roleId
     * @param
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId") Integer permissionId, @Param("roleId") Integer roleId);
}
