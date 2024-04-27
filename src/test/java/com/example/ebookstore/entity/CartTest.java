package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CartTest {

    @Test
    void setIdAndGetIdWorkCorrectly() {
        Cart cart = new Cart();
        cart.setId(1);
        assertEquals(1, cart.getId());
    }

    @Test
    void setUserIdAndGetUserIdWorkCorrectly() {
        Cart cart = new Cart();
        cart.setUserId(2);
        assertEquals(2, cart.getUserId());
    }

    @Test
    void setBookIdAndGetBookIdWorkCorrectly() {
        Cart cart = new Cart();
        cart.setBookId(3);
        assertEquals(3, cart.getBookId());
    }

    @Test
    void setBookNumAndGetBookNumWorkCorrectly() {
        Cart cart = new Cart();
        cart.setBookNum(4);
        assertEquals(4, cart.getBookNum());
    }

    @Test
    void equalsReturnsTrueForOneCart() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        assertEquals(cart1, cart1);
    }

    @Test
    void equalsReturnsTrueForSameCart() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(1);
        cart2.setUserId(2);
        cart2.setBookId(3);
        cart2.setBookNum(4);

        assertEquals(cart1, cart2);
    }

    @Test
    void equalsReturnsFalseForNull() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        assertNotEquals(cart1, null);
    }

    @Test
    void equalsReturnsFalseForDifferentObject() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        assertNotEquals(cart1, new Object());
    }

    @Test
    void equalsReturnsFalseForDifferentId() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(5);
        cart2.setUserId(6);
        cart2.setBookId(7);
        cart2.setBookNum(8);

        assertNotEquals(cart1, cart2);
    }

    @Test
    void equalsReturnsFalseForDifferentUserId() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(1);
        cart2.setUserId(6);
        cart2.setBookId(3);
        cart2.setBookNum(4);

        assertNotEquals(cart1, cart2);
    }

    @Test
    void equalsReturnsFalseForDifferentBookId() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(1);
        cart2.setUserId(2);
        cart2.setBookId(7);
        cart2.setBookNum(4);

        assertNotEquals(cart1, cart2);
    }

    @Test
    void equalsReturnsFalseForDifferentBookNum() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(1);
        cart2.setUserId(2);
        cart2.setBookId(3);
        cart2.setBookNum(8);

        assertNotEquals(cart1, cart2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameCart() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(1);
        cart2.setUserId(2);
        cart2.setBookId(3);
        cart2.setBookNum(4);

        assertEquals(cart1.hashCode(), cart2.hashCode());
    }

    @Test
    void hashCodeReturnsDifferentHashCodeForDifferentCarts() {
        Cart cart1 = new Cart();
        cart1.setId(1);
        cart1.setUserId(2);
        cart1.setBookId(3);
        cart1.setBookNum(4);

        Cart cart2 = new Cart();
        cart2.setId(5);
        cart2.setUserId(6);
        cart2.setBookId(7);
        cart2.setBookNum(8);

        assertNotEquals(cart1.hashCode(), cart2.hashCode());
    }
}