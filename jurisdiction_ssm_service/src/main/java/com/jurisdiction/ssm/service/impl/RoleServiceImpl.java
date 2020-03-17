package com.jurisdiction.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.jurisdiction.ssm.dao.IRoleDao;
import com.jurisdiction.ssm.domain.Permission;
import com.jurisdiction.ssm.domain.Role;
import com.jurisdiction.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//角色
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    //角色查询
    @Override
    public List<Role> findAll(int page, int size) throws Exception {
        //参数pagenum是页码值，pagesize代表每页显示条数
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    //角色添加
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }
//查询角色
    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }
//查询可添加的权限
    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }
//添加权限
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for(String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);

        }
    }

    @Override
    public void deleteRoleById(String roleId) throws Exception {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }
}
