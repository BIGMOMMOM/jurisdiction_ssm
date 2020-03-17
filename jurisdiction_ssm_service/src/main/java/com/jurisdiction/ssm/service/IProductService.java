package com.jurisdiction.ssm.service;


import com.jurisdiction.ssm.domain.Product;

import java.util.List;

/**
 * 查询所有产品信息
 */
public interface IProductService {
    public List<Product> findAll(int page, int size) throws Exception;

    void save(Product product) throws Exception;

    Product findById(String id) throws Exception;

   void updateById(String id) throws Exception;
   void deleteProduct(String id)throws  Exception;
}
