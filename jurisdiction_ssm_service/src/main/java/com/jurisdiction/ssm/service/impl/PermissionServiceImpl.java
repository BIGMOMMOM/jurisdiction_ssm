package com.jurisdiction.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.jurisdiction.ssm.dao.IPermissionDao;
import com.jurisdiction.ssm.domain.Permission;
import com.jurisdiction.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) throws Exception {
        //参数pagenum是页码值，pagesize代表每页显示条数
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
//删除权限
@Override
public void deleteById(String id) throws Exception {
    permissionDao.deleteFromRole_Permission(id);
    permissionDao.deleteById(id);
}
    //查询详细信息
    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }
}
