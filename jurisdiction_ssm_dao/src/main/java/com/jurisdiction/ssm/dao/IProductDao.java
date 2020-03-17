package com.jurisdiction.ssm.dao;


import com.jurisdiction.ssm.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {
    //查询所有的产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //保存
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    //根据id查询
    @Select("select*from product where id=#{id}")
    public Product findById(String id) throws Exception;

    //根据id查询数据并修改
    /**
     * 未完成的功能
     * */
    @Update("update  product set productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where product.id=#{id}")
    Product updateById(String id) throws Exception;
    //删除产品
    @Delete("delete  from  product where product.id=#{id}")
    void deleteProduct(String id)throws  Exception;
}
