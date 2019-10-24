package com.cbx.service;

import com.cbx.domain.Orders;

import java.util.List;

public  interface IOrderService {
    List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(Integer ordersId) throws Exception;
}
