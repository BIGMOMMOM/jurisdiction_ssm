package com.jurisdiction.ssm.dao;


import com.jurisdiction.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select*from permission")
    List<Permission> findAll() throws Exception;

    //查询权限的详细信息
    @Select("select * from permission where id=#{id}")
    Permission findById(String id) throws Exception;

    //权限添加
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    //权限删除
    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(String id) throws Exception;

    @Delete("delete from permission where id=#{id}")
    void deleteById(String id) throws Exception ;
}
