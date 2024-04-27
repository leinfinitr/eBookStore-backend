package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderlistTest {

    @Test
    void setIdAndGetIdWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setOrderId(1);
        assertEquals(1, orderlist.getOrderId());
    }

    @Test
    void setUserNameAndGetUserNameWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setUserName("Test User");
        assertEquals("Test User", orderlist.getUserName());
    }

    @Test
    void setPhoneAndGetPhoneWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setPhone("1234567890");
        assertEquals("1234567890", orderlist.getPhone());
    }

    @Test
    void setNationAndGetNationWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setNation("Test Nation");
        assertEquals("Test Nation", orderlist.getNation());
    }

    @Test
    void setProvinceAndGetProvinceWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setProvince("Test Province");
        assertEquals("Test Province", orderlist.getProvince());
    }

    @Test
    void setAddressAndGetAddressWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setAddress("Test Address");
        assertEquals("Test Address", orderlist.getAddress());
    }

    @Test
    void setTotalPriceAndGetTotalPriceWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        orderlist.setTotalPrice(BigDecimal.valueOf(10.99));
        assertEquals(BigDecimal.valueOf(10.99), orderlist.getTotalPrice());
    }

    @Test
    void setTimeAndGetTimeWorkCorrectly() {
        Orderlist orderlist = new Orderlist();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orderlist.setTime(timestamp);
        assertEquals(timestamp, orderlist.getTime());
    }

    @Test
    void equalsReturnsTrueForSameOrderlist() {
        Orderlist orderlist1 = new Orderlist();
        orderlist1.setOrderId(1);
        orderlist1.setUserName("Test User");
        orderlist1.setPhone("1234567890");
        orderlist1.setNation("Test Nation");
        orderlist1.setProvince("Test Province");
        orderlist1.setAddress("Test Address");
        orderlist1.setTotalPrice(BigDecimal.valueOf(10.99));
        orderlist1.setTime(new Timestamp(System.currentTimeMillis()));

        Orderlist orderlist2 = new Orderlist();
        orderlist2.setOrderId(1);
        orderlist2.setUserName("Test User");
        orderlist2.setPhone("1234567890");
        orderlist2.setNation("Test Nation");
        orderlist2.setProvince("Test Province");
        orderlist2.setAddress("Test Address");
        orderlist2.setTotalPrice(BigDecimal.valueOf(10.99));
        orderlist2.setTime(new Timestamp(System.currentTimeMillis()));

        assertEquals(orderlist1, orderlist2);
    }

    @Test
    void equalsReturnsFalseForDifferentOrderlists() {
        Orderlist orderlist1 = new Orderlist();
        orderlist1.setOrderId(1);
        orderlist1.setUserName("Test User");
        orderlist1.setPhone("1234567890");
        orderlist1.setNation("Test Nation");
        orderlist1.setProvince("Test Province");
        orderlist1.setAddress("Test Address");
        orderlist1.setTotalPrice(BigDecimal.valueOf(10.99));
        orderlist1.setTime(new Timestamp(System.currentTimeMillis()));

        Orderlist orderlist2 = new Orderlist();
        orderlist2.setOrderId(2);
        orderlist2.setUserName("Different Test User");
        orderlist2.setPhone("0987654321");
        orderlist2.setNation("Different Test Nation");
        orderlist2.setProvince("Different Test Province");
        orderlist2.setAddress("Different Test Address");
        orderlist2.setTotalPrice(BigDecimal.valueOf(20.99));
        orderlist2.setTime(new Timestamp(System.currentTimeMillis()));

        assertNotEquals(orderlist1, orderlist2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameOrderlist() {
        Orderlist orderlist1 = new Orderlist();
        orderlist1.setOrderId(1);
        orderlist1.setUserName("Test User");
        orderlist1.setPhone("1234567890");
        orderlist1.setNation("Test Nation");
        orderlist1.setProvince("Test Province");
        orderlist1.setAddress("Test Address");
        orderlist1.setTotalPrice(BigDecimal.valueOf(10.99));
        orderlist1.setTime(new Timestamp(System.currentTimeMillis()));

        Orderlist orderlist2 = new Orderlist();
        orderlist2.setOrderId(1);
        orderlist2.setUserName("Test User");
        orderlist2.setPhone("1234567890");
        orderlist2.setNation("Test Nation");
        orderlist2.setProvince("Test Province");
        orderlist2.setAddress("Test Address");
        orderlist2.setTotalPrice(BigDecimal.valueOf(10.99));
        orderlist2.setTime(new Timestamp(System.currentTimeMillis()));

        assertEquals(orderlist1.hashCode(), orderlist2.hashCode());
    }

    @Test
    void hashCodeReturnsDifferentHashCodeForDifferentOrderlists() {
        Orderlist orderlist1 = new Orderlist();
        orderlist1.setOrderId(1);
        orderlist1.setUserName("Test User");
        orderlist1.setPhone("1234567890");
        orderlist1.setNation("Test Nation");
        orderlist1.setProvince("Test Province");
        orderlist1.setAddress("Test Address");
        orderlist1.setTotalPrice(BigDecimal.valueOf(10.99));
        orderlist1.setTime(new Timestamp(System.currentTimeMillis()));

        Orderlist orderlist2 = new Orderlist();
        orderlist2.setOrderId(2);
        orderlist2.setUserName("Different Test User");
        orderlist2.setPhone("0987654321");
        orderlist2.setNation("Different Test Nation");
        orderlist2.setProvince("Different Test Province");
        orderlist2.setAddress("Different Test Address");
        orderlist2.setTotalPrice(BigDecimal.valueOf(20.99));
        orderlist2.setTime(new Timestamp(System.currentTimeMillis()));

        assertNotEquals(orderlist1.hashCode(), orderlist2.hashCode());
    }
}