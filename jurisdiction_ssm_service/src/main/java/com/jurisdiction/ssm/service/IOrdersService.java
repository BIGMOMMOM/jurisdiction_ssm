package com.jurisdiction.ssm.service;


import com.jurisdiction.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    //查询所有
    List<Orders> findAll(int page, int size)throws Exception;

    Orders findById(String ordersId)throws Exception;
    //删除
   void deleteOrders(String id)throws  Exception;
}
