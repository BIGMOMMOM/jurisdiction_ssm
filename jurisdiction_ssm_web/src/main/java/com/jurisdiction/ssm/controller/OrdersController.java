package com.jurisdiction.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jurisdiction.ssm.domain.Orders;
import com.jurisdiction.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

 /*       //查询全部订单为分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Orders> all = ordersService.findAll();
        mv.addObject("ordersList",all);
        mv.setViewName("orders-list");
        return mv;
    }*/
    @RequestMapping("/deleteOrder.do")
    //删除用户角色
    public String deleteRoleToUser(@RequestParam(name = "id", required = true) String order) throws Exception {
        ordersService.deleteOrders(order);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
