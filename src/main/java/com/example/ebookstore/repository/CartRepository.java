package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserIdAndBookId(int userId, int bookId);

    List<Cart> findCartsByUserId(int userId);
}
