package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Cart;
import com.example.ebookstore.serviceimpl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {
    @Mock
    private CartServiceImpl cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCartByUserId() {
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setBookId(1);
        cart.setBookNum(1);

        when(cartService.findCartsByUserId(1)).thenReturn(List.of(cart));

        List<Cart> actualCart = cartController.getCartByUserId("1");

        assertEquals(List.of(cart), actualCart);
    }

    @Test
    void addCartSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("bookId", "1");
        params.put("bookNum", "1");

        Cart expectedCart = new Cart();
        expectedCart.setUserId(1);
        expectedCart.setBookId(1);
        expectedCart.setBookNum(1);

        when(cartService.addByUidBidBnum(1, 1, 1)).thenReturn(expectedCart);

        Cart actualCart = cartController.addCart(params);

        assertEquals(expectedCart, actualCart);
    }

    @Test
    void deleteCartSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("bookId", "1");

        doNothing().when(cartService).deleteByUserIdAndBookId(1, 1);

        cartController.deleteCart(params);

        verify(cartService).deleteByUserIdAndBookId(1, 1);
    }

    @Test
    void modifyCartSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("bookId", "1");
        params.put("bookNum", "1");

        Cart expectedCart = new Cart();
        expectedCart.setUserId(1);
        expectedCart.setBookId(1);
        expectedCart.setBookNum(1);

        when(cartService.modifyByUidBidBnum(1, 1, 1)).thenReturn(expectedCart);

        Cart actualCart = cartController.modifyCart(params);

        assertEquals(expectedCart, actualCart);
    }
}