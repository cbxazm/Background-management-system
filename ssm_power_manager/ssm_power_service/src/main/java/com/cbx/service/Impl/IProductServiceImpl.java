package com.cbx.service.Impl;

import com.cbx.dao.IProductDao;
import com.cbx.domain.Product;
import com.cbx.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {
    @Autowired
       private IProductDao iProductDao;
    @Override
    public List<Product> findAll()
    {
        return iProductDao.findAll();
    }

    @Override
    public void save(Product product) {
                iProductDao.save(product);
    }
}
