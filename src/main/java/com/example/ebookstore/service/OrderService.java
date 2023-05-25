package com.example.ebookstore.service;

import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;

import java.util.List;

public interface OrderService {
    List<Orderlist> findOrderlistsByUserName(String userName);

    List<Orderitem> findOrderitemsByOrderId(Integer orderId);

    Integer addOrderlistByUnamePrice(String userName, Double price);

    void addOrderitemByOidBidBnumPay(Integer orderId, Integer bookId, Integer bookNum, Double pay);
}
