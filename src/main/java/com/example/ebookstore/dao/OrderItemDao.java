package com.example.ebookstore.dao;

import com.example.ebookstore.entity.Orderitem;

import java.util.List;

public interface OrderItemDao {
    List<Orderitem> findOrderitemsByOrderId(Integer orderId);

    Orderitem saveOrderitem(Orderitem orderitem);

    List<Orderitem> findOrderitems();
}
