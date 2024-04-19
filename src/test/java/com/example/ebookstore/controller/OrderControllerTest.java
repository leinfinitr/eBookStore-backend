package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class OrderControllerTest {
    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOrdersByUserNameReturnsOrders() {
        String name = "testUser";
        List<Orderlist> expectedOrders = new ArrayList<>();
        when(orderService.findOrderlistsByUserName(name)).thenReturn(expectedOrders);

        List<Orderlist> actualOrders = orderController.getOrders(name);

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void getOrdersReturnsAllOrders() {
        List<Orderlist> expectedOrders = new ArrayList<>();
        when(orderService.findOrderlists()).thenReturn(expectedOrders);

        List<Orderlist> actualOrders = orderController.getOrders();

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void getOrderItemsByUserNameReturnsOrderItems() {
        String name = "testUser";
        List<Orderitem> expectedOrderItems = new ArrayList<>();
        when(orderService.findOrderitemsByUserName(name)).thenReturn(expectedOrderItems);

        List<Orderitem> actualOrderItems = orderController.getOrderItemsByUserName(name);

        assertEquals(expectedOrderItems, actualOrderItems);
    }

    @Test
    void getOrderItemsByOrderIdReturnsOrderItems() {
        Integer orderId = 1;
        List<Orderitem> expectedOrderItems = new ArrayList<>();
        when(orderService.findOrderitemsByOrderId(orderId)).thenReturn(expectedOrderItems);

        List<Orderitem> actualOrderItems = orderController.getOrderItems(orderId);

        assertEquals(expectedOrderItems, actualOrderItems);
    }

    @Test
    void getOrderItemsReturnsAllOrderItems() {
        List<Orderitem> expectedOrderItems = new ArrayList<>();
        when(orderService.findOrderitems()).thenReturn(expectedOrderItems);

        List<Orderitem> actualOrderItems = orderController.getOrderItems();

        assertEquals(expectedOrderItems, actualOrderItems);
    }

    @Test
    void buyReturnsSuccessWhenOrderIsValid() {
        Map<String, Object> order = new HashMap<>();
        order.put("userName", "testUser");
        order.put("totalPrice", 100.0);
        List<Map<String, Object>> orderlistItems = new ArrayList<>();
        Orderitem orderitem = new Orderitem();
        orderitem.setId(1);
        orderitem.setOrderId(1);
        orderitem.setName("testItem");
        orderitem.setPrice(BigDecimal.valueOf(100.0));
        orderitem.setImage("testImage");
        orderitem.setNum(1);
        orderitem.setPay(BigDecimal.valueOf(100.0));
        orderlistItems.add(Map.of(
                "id", orderitem.getId(),
                "orderId", orderitem.getOrderId(),
                "name", orderitem.getName(),
                "price", orderitem.getPrice(),
                "image", orderitem.getImage(),
                "num", orderitem.getNum(),
                "pay", orderitem.getPay()
        ));

        order.put("orderlist", orderlistItems);

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", 200);

        when(orderService.addOrderlistByUnamePrice("testUser", 100.0)).thenReturn(1);
        doNothing().when(orderService).addOrderitem(any(), anyInt());

        Map<String, Object> actualResult = orderController.buy(order);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void buyReturnsSuccessWhenOrderIsValidWithIntegerTotalPrice() {
        Map<String, Object> order = new HashMap<>();
        order.put("userName", "testUser");
        order.put("totalPrice", 100);
        List<Map<String, Object>> orderlistItems = new ArrayList<>();
        Orderitem orderitem = new Orderitem();
        orderitem.setId(1);
        orderitem.setOrderId(1);
        orderitem.setName("testItem");
        orderitem.setPrice(BigDecimal.valueOf(100.0));
        orderitem.setImage("testImage");
        orderitem.setNum(1);
        orderitem.setPay(BigDecimal.valueOf(100.0));
        orderlistItems.add(Map.of(
                "id", orderitem.getId(),
                "orderId", orderitem.getOrderId(),
                "name", orderitem.getName(),
                "price", orderitem.getPrice(),
                "image", orderitem.getImage(),
                "num", orderitem.getNum(),
                "pay", orderitem.getPay()
        ));

        order.put("orderlist", orderlistItems);

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", 200);

        when(orderService.addOrderlistByUnamePrice("testUser", 100.0)).thenReturn(1);
        doNothing().when(orderService).addOrderitem(any(), anyInt());

        Map<String, Object> actualResult = orderController.buy(order);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void buyReturnsSuccessWhenOrderIsValidWithStringTotalPrice() {
        Map<String, Object> order = new HashMap<>();
        order.put("userName", "testUser");
        order.put("totalPrice", "100");
        List<Map<String, Object>> orderlistItems = new ArrayList<>();
        Orderitem orderitem = new Orderitem();
        orderitem.setId(1);
        orderitem.setOrderId(1);
        orderitem.setName("testItem");
        orderitem.setPrice(BigDecimal.valueOf(100.0));
        orderitem.setImage("testImage");
        orderitem.setNum(1);
        orderitem.setPay(BigDecimal.valueOf(100.0));
        orderlistItems.add(Map.of(
                "id", orderitem.getId(),
                "orderId", orderitem.getOrderId(),
                "name", orderitem.getName(),
                "price", orderitem.getPrice(),
                "image", orderitem.getImage(),
                "num", orderitem.getNum(),
                "pay", orderitem.getPay()
        ));

        order.put("orderlist", orderlistItems);

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", 200);

        when(orderService.addOrderlistByUnamePrice("testUser", 100.0)).thenReturn(1);
        doNothing().when(orderService).addOrderitem(any(), anyInt());

        Map<String, Object> actualResult = orderController.buy(order);

        assertEquals(expectedResult, actualResult);
    }
}