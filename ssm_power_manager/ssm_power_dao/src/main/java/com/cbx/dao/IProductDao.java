package com.cbx.dao;

import com.cbx.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {
    //根据id查询产品
    @Select("select * from product where id=#{id}")
    public Product findById(int id);
    //查询所有的产品信息
    @Select("select * from product")
    public List<Product> findAll();
  @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
@Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{productId} ")
    Product saveProductId(Integer productId);
}
