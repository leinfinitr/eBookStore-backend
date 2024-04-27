package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OrderitemTest {

    @Test
    void setIdAndGetIdWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setId(1);
        assertEquals(1, orderitem.getId());
    }

    @Test
    void setOrderIdAndGetOrderIdWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setOrderId(2);
        assertEquals(2, orderitem.getOrderId());
    }

    @Test
    void setNameAndGetNameWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setName("Test Name");
        assertEquals("Test Name", orderitem.getName());
    }

    @Test
    void setPriceAndGetPriceWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setPrice(BigDecimal.valueOf(10.99));
        assertEquals(BigDecimal.valueOf(10.99), orderitem.getPrice());
    }

    @Test
    void setImageAndGetImageWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setImage("Test Image");
        assertEquals("Test Image", orderitem.getImage());
    }

    @Test
    void setNumAndGetNumWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setNum(4);
        assertEquals(4, orderitem.getNum());
    }

    @Test
    void setPayAndGetPayWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setPay(BigDecimal.valueOf(20.99));
        assertEquals(BigDecimal.valueOf(20.99), orderitem.getPay());
    }

    @Test
    void setTimeAndGetTimeWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orderitem.setTime(timestamp);
        assertEquals(timestamp, orderitem.getTime());
    }

    @Test
    void setStatusAndGetStatusWorkCorrectly() {
        Orderitem orderitem = new Orderitem();
        orderitem.setStatus("Test Status");
        assertEquals("Test Status", orderitem.getStatus());
    }

    @Test
    void equalsReturnsTrueForSameOrderitem() {
        Orderitem orderitem1 = new Orderitem();
        orderitem1.setId(1);
        orderitem1.setOrderId(2);
        orderitem1.setName("Test Name");
        orderitem1.setPrice(BigDecimal.valueOf(10.99));
        orderitem1.setImage("Test Image");
        orderitem1.setNum(4);
        orderitem1.setPay(BigDecimal.valueOf(20.99));
        orderitem1.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem1.setStatus("Test Status");

        Orderitem orderitem2 = new Orderitem();
        orderitem2.setId(1);
        orderitem2.setOrderId(2);
        orderitem2.setName("Test Name");
        orderitem2.setPrice(BigDecimal.valueOf(10.99));
        orderitem2.setImage("Test Image");
        orderitem2.setNum(4);
        orderitem2.setPay(BigDecimal.valueOf(20.99));
        orderitem2.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem2.setStatus("Test Status");

        assertEquals(orderitem1, orderitem2);
    }

    @Test
    void equalsReturnsFalseForDifferentOrderitems() {
        Orderitem orderitem1 = new Orderitem();
        orderitem1.setId(1);
        orderitem1.setOrderId(2);
        orderitem1.setName("Test Name");
        orderitem1.setPrice(BigDecimal.valueOf(10.99));
        orderitem1.setImage("Test Image");
        orderitem1.setNum(4);
        orderitem1.setPay(BigDecimal.valueOf(20.99));
        orderitem1.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem1.setStatus("Test Status");

        Orderitem orderitem2 = new Orderitem();
        orderitem2.setId(3);
        orderitem2.setOrderId(4);
        orderitem2.setName("Different Test Name");
        orderitem2.setPrice(BigDecimal.valueOf(30.99));
        orderitem2.setImage("Different Test Image");
        orderitem2.setNum(5);
        orderitem2.setPay(BigDecimal.valueOf(40.99));
        orderitem2.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem2.setStatus("Different Test Status");

        assertNotEquals(orderitem1, orderitem2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameOrderitem() {
        Orderitem orderitem1 = new Orderitem();
        orderitem1.setId(1);
        orderitem1.setOrderId(2);
        orderitem1.setName("Test Name");
        orderitem1.setPrice(BigDecimal.valueOf(10.99));
        orderitem1.setImage("Test Image");
        orderitem1.setNum(4);
        orderitem1.setPay(BigDecimal.valueOf(20.99));
        orderitem1.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem1.setStatus("Test Status");

        Orderitem orderitem2 = new Orderitem();
        orderitem2.setId(1);
        orderitem2.setOrderId(2);
        orderitem2.setName("Test Name");
        orderitem2.setPrice(BigDecimal.valueOf(10.99));
        orderitem2.setImage("Test Image");
        orderitem2.setNum(4);
        orderitem2.setPay(BigDecimal.valueOf(20.99));
        orderitem2.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem2.setStatus("Test Status");

        assertEquals(orderitem1.hashCode(), orderitem2.hashCode());
    }

    @Test
    void hashCodeReturnsDifferentHashCodeForDifferentOrderitems() {
        Orderitem orderitem1 = new Orderitem();
        orderitem1.setId(1);
        orderitem1.setOrderId(2);
        orderitem1.setName("Test Name");
        orderitem1.setPrice(BigDecimal.valueOf(10.99));
        orderitem1.setImage("Test Image");
        orderitem1.setNum(4);
        orderitem1.setPay(BigDecimal.valueOf(20.99));
        orderitem1.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem1.setStatus("Test Status");

        Orderitem orderitem2 = new Orderitem();
        orderitem2.setId(3);
        orderitem2.setOrderId(4);
        orderitem2.setName("Different Test Name");
        orderitem2.setPrice(BigDecimal.valueOf(30.99));
        orderitem2.setImage("Different Test Image");
        orderitem2.setNum(5);
        orderitem2.setPay(BigDecimal.valueOf(40.99));
        orderitem2.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem2.setStatus("Different Test Status");

        assertNotEquals(orderitem1.hashCode(), orderitem2.hashCode());
    }
}