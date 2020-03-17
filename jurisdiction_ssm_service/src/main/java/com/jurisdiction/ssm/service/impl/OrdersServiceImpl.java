package com.jurisdiction.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.jurisdiction.ssm.dao.IOrdersDao;
import com.jurisdiction.ssm.domain.Orders;
import com.jurisdiction.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pagenum是页码值，pagesize代表每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

    @Override
    public void deleteOrders(String id) throws Exception {
       ordersDao.deleteOrdersTraveller(id);
      ordersDao.deleteOrders(id);
    }
}
