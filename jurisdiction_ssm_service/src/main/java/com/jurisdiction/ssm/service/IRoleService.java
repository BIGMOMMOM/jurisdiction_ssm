package com.jurisdiction.ssm.service;


import com.jurisdiction.ssm.domain.Permission;
import com.jurisdiction.ssm.domain.Role;

import java.util.List;

//角色
public interface IRoleService {
    public List<Role> findAll(int page, int size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
    void deleteRoleById(String roleId) throws Exception;
}
