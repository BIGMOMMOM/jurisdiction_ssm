package com.jurisdiction.ssm.dao;


import com.jurisdiction.ssm.domain.Role;
import com.jurisdiction.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.jurisdiction.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select*from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select*from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.jurisdiction.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    //通过ID查询可以添加的角色信息
    @Select("select*from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    //添加角色信息

    /**
     * @Param("userId") String userId
     * @Param("roleId") String roleId
     * 是给方法指定一个名字
     */
    @Insert("insert into  users_role(userId,roleId)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;

    //通过ID查询已经拥有的角色信息
    // @Select("select*from role where id in(select roleId from users_role where userId=#{userId})")
    @Select("select role.id , role.roleName,role.roleDesc from users ,role ,users_role  where users.id=users_role.userId and users.id=#{userId} and role.id= users_role.roleId")
    List<Role> findRoles(String userId) throws Exception;

    //删除角色
    @Delete("delete from users_role where users_role.roleId=#{roleId}")
    void deleteRoleToUser(String roleId) throws Exception;
}
