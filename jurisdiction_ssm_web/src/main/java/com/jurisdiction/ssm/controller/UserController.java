package com.jurisdiction.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jurisdiction.ssm.domain.Role;
import com.jurisdiction.ssm.domain.UserInfo;
import com.jurisdiction.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/addRoleToUser.do")
    //给用户添加角色
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deleteRoleToUser.do")
    //删除用户角色
    public String deleteRoleToUser(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        userService.deleteRoleToUser(roleId);
        return "redirect:findAll.do";
    }
   //根据id查询用户和用户已经拥有的角色
    @RequestMapping("/findUserByIdAndRoles.do")
    public ModelAndView findUserByIdAndRoles(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户Id查询用户
        UserInfo userInfo = userService.findById(userId);
        // 根据用户ID查询已经添加的角色信息
        List<Role> otherRole = userService.findRoles(userId);
        mv.addObject("user", userInfo);
        mv.addObject("otherRole", otherRole);
        mv.setViewName("users-role-delete");
        return mv;
    }
    //根据id查询用户和用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户Id查询用户
        UserInfo userInfo = userService.findById(userid);
        // 根据用户ID查询可以添加的角色信息
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    //查询指定ID的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //用户的添加
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

//查询所有
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userInfoList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    /*@RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }*/
}
