package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Orderlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderlistRepository extends JpaRepository<Orderlist, Integer> {
    List<Orderlist> findOrderlistsByUserName(String userName);
}
