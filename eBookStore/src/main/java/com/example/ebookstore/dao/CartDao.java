package com.example.ebookstore.dao;

import com.example.ebookstore.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> findCartsByUserId(Integer userId);

    Cart findCartByUserIdAndBookId(Integer userId, Integer bookId);

    Cart save(Cart cart);

    void deleteByCart(Cart cart);
}
