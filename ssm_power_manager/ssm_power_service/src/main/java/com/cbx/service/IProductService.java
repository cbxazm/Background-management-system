package com.cbx.service;

import com.cbx.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    void save(Product product);
    Product findById(Integer productId);

    void saveProduct(Integer productId);
}
