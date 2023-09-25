package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.repository.OrderlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderlistRepository orderlistRepository;

    @Override
    public List<Orderlist> findOrderslistsByUserName(String userName) {
        return orderlistRepository.findOrderlistsByUserName(userName);
    }

    @Override
    public List<Orderlist> findOrderlists() {
        return orderlistRepository.findAll();
    }

    @Override
    @Transactional
    public Orderlist saveOrderlist(Orderlist orderlist) {
        return orderlistRepository.save(orderlist);
    }

}
