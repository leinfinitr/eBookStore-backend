package com.example.ebookstore.service;

import com.example.ebookstore.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findCartsByUserId(Integer userId);

    Cart findCartByUserIdAndBookId(Integer userId, Integer bookId);

    Cart addByUidBidBnum(Integer userId, Integer bookId, Integer bookNum);

    Cart modifyByUidBidBnum(Integer userId, Integer bookId, Integer bookNum);

    void deleteByUserIdAndBookId(Integer userId, Integer bookId);
}
