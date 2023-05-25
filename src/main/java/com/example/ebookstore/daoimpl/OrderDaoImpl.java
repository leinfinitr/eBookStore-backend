package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.repository.OrderitemRepository;
import com.example.ebookstore.repository.OrderlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderitemRepository orderitemRepository;
    @Autowired
    private OrderlistRepository orderlistRepository;

    @Override
    public List<Orderlist> findOrderslistsByUserName(String userName) {
        return orderlistRepository.findOrderlistsByUserName(userName);
    }

    @Override
    public List<Orderitem> findOrderitemsByOrderId(Integer orderId) {
        return orderitemRepository.findOrderitemsByOrderId(orderId);
    }

    @Override
    public Orderlist save(Orderlist orderlist) {
        return orderlistRepository.save(orderlist);
    }

    @Override
    public Orderitem save(Orderitem orderitem) {
        return orderitemRepository.save(orderitem);
    }
}
