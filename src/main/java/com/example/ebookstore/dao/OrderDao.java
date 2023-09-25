package com.example.ebookstore.dao;

import com.example.ebookstore.entity.Orderlist;

import java.util.List;

public interface OrderDao {
    List<Orderlist> findOrderslistsByUserName(String userName);

    Orderlist saveOrderlist(Orderlist orderlist);

    List<Orderlist> findOrderlists();
}
