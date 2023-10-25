package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.CartDao;
import com.example.ebookstore.entity.Cart;
import com.example.ebookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Override
    public List<Cart> findCartsByUserId(Integer userId) {
        return cartDao.findCartsByUserId(userId);
    }

    @Override
    public Cart findCartByUserIdAndBookId(Integer userId, Integer bookId) {
        return cartDao.findCartByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Cart addByUidBidBnum(Integer userId, Integer bookId, Integer bookNum) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBookId(bookId);
        cart.setBookNum(bookNum);
        return cartDao.save(cart);
    }

    @Override
    public Cart modifyByUidBidBnum(Integer userId, Integer bookId, Integer bookNum) {
        Cart cart = cartDao.findCartByUserIdAndBookId(userId, bookId);
        cart.setBookNum(bookNum);
        return cartDao.save(cart);
    }

    @Override
    public void deleteByUserIdAndBookId(Integer userId, Integer bookId) {
        Cart cart = cartDao.findCartByUserIdAndBookId(userId, bookId);
        cartDao.deleteByCart(cart);
    }
}
