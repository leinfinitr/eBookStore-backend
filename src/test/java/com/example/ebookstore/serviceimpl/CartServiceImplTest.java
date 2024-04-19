package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.CartDao;
import com.example.ebookstore.entity.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartDao cartDao;

    @InjectMocks
    private CartServiceImpl cartServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findCartsByUserIdReturnsCarts() {
        Integer userId = 1;
        List<Cart> expectedCarts = new ArrayList<>();
        when(cartDao.findCartsByUserId(userId)).thenReturn(expectedCarts);

        List<Cart> actualCarts = cartServiceImpl.findCartsByUserId(userId);

        assertEquals(expectedCarts, actualCarts);
    }

    @Test
    void findCartByUserIdAndBookIdReturnsCart() {
        Integer userId = 1;
        Integer bookId = 1;
        Cart expectedCart = new Cart();
        when(cartDao.findCartByUserIdAndBookId(userId, bookId)).thenReturn(expectedCart);

        Cart actualCart = cartServiceImpl.findCartByUserIdAndBookId(userId, bookId);

        assertEquals(expectedCart, actualCart);
    }

    @Test
    void addByUidBidBnumAddsCartSuccessfully() {
        Integer userId = 1;
        Integer bookId = 1;
        Integer bookNum = 1;
        Cart expectedCart = new Cart();
        expectedCart.setUserId(userId);
        expectedCart.setBookId(bookId);
        expectedCart.setBookNum(bookNum);
        when(cartDao.save(any(Cart.class))).thenReturn(expectedCart);

        Cart actualCart = cartServiceImpl.addByUidBidBnum(userId, bookId, bookNum);

        assertEquals(expectedCart, actualCart);
    }

    @Test
    void modifyByUidBidBnumModifiesCartSuccessfully() {
        Integer userId = 1;
        Integer bookId = 1;
        Integer bookNum = 1;
        Cart expectedCart = new Cart();
        expectedCart.setUserId(userId);
        expectedCart.setBookId(bookId);
        expectedCart.setBookNum(bookNum);
        when(cartDao.findCartByUserIdAndBookId(userId, bookId)).thenReturn(expectedCart);
        when(cartDao.save(any(Cart.class))).thenReturn(expectedCart);

        Cart actualCart = cartServiceImpl.modifyByUidBidBnum(userId, bookId, bookNum);

        assertEquals(expectedCart, actualCart);
    }

    @Test
    void deleteByUserIdAndBookIdDeletesCartSuccessfully() {
        Integer userId = 1;
        Integer bookId = 1;
        Cart cart = new Cart();
        when(cartDao.findCartByUserIdAndBookId(userId, bookId)).thenReturn(cart);
        doNothing().when(cartDao).deleteByCart(cart);

        cartServiceImpl.deleteByUserIdAndBookId(userId, bookId);

        verify(cartDao).deleteByCart(cart);
    }
}