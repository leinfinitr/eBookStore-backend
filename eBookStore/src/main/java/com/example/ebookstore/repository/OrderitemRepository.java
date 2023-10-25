package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderitemRepository extends JpaRepository<Orderitem, Integer> {
    List<Orderitem> findOrderitemsByOrderId(Integer orderId);
}
