package com.example.ebookstore.dao;

import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;

import java.util.List;

public interface OrderDao {
    List<Orderlist> findOrderslistsByUserName(String userName);

    List<Orderitem> findOrderitemsByOrderId(Integer orderId);

    Orderlist save(Orderlist orderlist);

    Orderitem save(Orderitem orderitem);

    void deleteOrderlistByOrderId(Integer orderlistId);

    List<Orderlist> findOrderlists();

    List<Orderitem> findOrderitems();
}
