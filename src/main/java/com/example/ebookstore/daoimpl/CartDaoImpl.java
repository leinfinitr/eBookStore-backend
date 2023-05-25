package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.CartDao;
import com.example.ebookstore.entity.Cart;
import com.example.ebookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findCartsByUserId(Integer userId) {
        return cartRepository.findCartsByUserId(userId);
    }

    @Override
    public Cart findCartByUserIdAndBookId(Integer userId, Integer bookId) {
        return cartRepository.findByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteByCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
