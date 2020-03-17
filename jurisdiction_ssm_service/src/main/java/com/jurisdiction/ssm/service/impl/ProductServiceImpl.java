package com.jurisdiction.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.jurisdiction.ssm.dao.IProductDao;
import com.jurisdiction.ssm.domain.Product;
import com.jurisdiction.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//service的实现类
@Service
@Transactional//事务
public class ProductServiceImpl implements IProductService {
    @Autowired
    //通过productdao来实现方法
    private IProductDao productDao;
   /* @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }*/

    @Override
    public List<Product> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    //保存
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    //查询详情
    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }

    //修改
    @Override
    public void updateById(String id) throws Exception {
       productDao.updateById(id);
    }
//删除
    @Override
    public void deleteProduct(String id) throws Exception {
        productDao.deleteProduct(id);
    }
}
