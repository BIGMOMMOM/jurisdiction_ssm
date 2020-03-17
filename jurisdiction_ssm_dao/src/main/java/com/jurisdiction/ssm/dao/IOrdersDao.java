package com.jurisdiction.ssm.dao;


import com.jurisdiction.ssm.domain.Member;
import com.jurisdiction.ssm.domain.Orders;
import com.jurisdiction.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    //查询所有
    @Select("select*from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.jurisdiction.ssm.dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws  Exception;

    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.jurisdiction.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.jurisdiction.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.jurisdiction.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId)throws Exception;
    //删除订单
    @Delete("delete from  order_traveller where order_traveller.orderId=#{id}")
    void deleteOrdersTraveller(String id)throws  Exception;
    @Delete("delete from  orders where orders.id=#{id}")
    void deleteOrders(String id)throws  Exception;
}
