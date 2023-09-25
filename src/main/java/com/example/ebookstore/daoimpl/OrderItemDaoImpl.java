package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.OrderItemDao;
import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.repository.OrderitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderitemRepository orderitemRepository;

    @Override
    public List<Orderitem> findOrderitems() {
        return orderitemRepository.findAll();
    }

    @Override
    public List<Orderitem> findOrderitemsByOrderId(Integer orderId) {
        return orderitemRepository.findOrderitemsByOrderId(orderId);
    }

    @Override
    @Transactional
    public Orderitem saveOrderitem(Orderitem orderitem) {
        return orderitemRepository.save(orderitem);
    }
}
