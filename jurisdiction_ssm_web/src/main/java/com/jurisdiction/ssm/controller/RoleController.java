package com.jurisdiction.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jurisdiction.ssm.domain.Permission;
import com.jurisdiction.ssm.domain.Role;
import com.jurisdiction.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//角色
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    //删除角色
    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";//展示删除后的角色页面
    }

    //查询角色详细信息
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    //给角色添加权限的方法
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    //根据我们的roleId查询role,并查询出可添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
//根据我们的roleId查询role
        Role role = roleService.findById(roleId);
// 并查询出可添加的权限
        List<Permission> permissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //查询所有
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

   /* @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }*/
}
