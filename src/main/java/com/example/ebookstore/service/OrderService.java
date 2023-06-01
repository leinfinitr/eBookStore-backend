package com.example.ebookstore.service;

import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Orderlist> findOrderlistsByUserName(String userName);

    List<Orderitem> findOrderitemsByOrderId(Integer orderId);

    Integer addOrderlistByUnamePrice(String userName, Double price);

    void addOrderitem(Map<String, Object> map, Integer orderId);

    void deleteOrderlistByOrderId(Integer orderlistId);

    List<Orderitem> findOrderitemsByUserName(String name);

    List<Orderlist> findOrderlists();

    List<Orderitem> findOrderitems();
}
