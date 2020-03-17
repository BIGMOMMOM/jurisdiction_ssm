package com.jurisdiction.ssm.service;


import com.jurisdiction.ssm.domain.Role;
import com.jurisdiction.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(int page, int size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    //通过ID查询可以添加的角色信息
    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;

    //通过ID查询已经拥有的角色信息
    List<Role> findRoles(String userId) throws Exception;

    //删除角色
    void deleteRoleToUser(String roleId) throws Exception;
 }
