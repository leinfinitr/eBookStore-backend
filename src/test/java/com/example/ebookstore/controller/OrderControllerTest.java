package com.example.ebookstore.controller;

import com.example.ebookstore.entity.*;
import com.example.ebookstore.service.BookService;
import com.example.ebookstore.service.CartService;
import com.example.ebookstore.service.OrderService;
import com.example.ebookstore.service.UserService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class OrderControllerTest {
    @Mock
    OrderService orderService;

    @Mock
    BookService bookservice;

    @Mock
    CartService cartService;

    @Mock
    UserService userService;

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

    @Test
    void buyByIdWhenCartIdIsZero() {
        Integer cartId = 0;
        int userId = 1;

        List<Book> bookData = new ArrayList<>();
        Book book0 = new Book();
        book0.setId(0);
        book0.setIsbn("testIsbn");
        book0.setName("testBook");
        book0.setType("testType");
        book0.setAuthor("testAuthor");
        book0.setPrice(BigDecimal.valueOf(100.0));
        book0.setDescription("testDescription");
        book0.setInventory(10);
        book0.setImage("testImage");
        bookData.add(book0);

        Book book = new Book();
        book.setId(1);
        book.setIsbn("testIsbn");
        book.setName("testBook");
        book.setType("testType");
        book.setAuthor("testAuthor");
        book.setPrice(BigDecimal.valueOf(100.0));
        book.setDescription("testDescription");
        book.setInventory(10);
        book.setImage("testImage");
        bookData.add(book);

        List<Cart> cartData = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(0);
        cart.setUserId(1);
        cart.setBookId(1);
        cart.setBookNum(1);
        cartData.add(cart);

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);
        when(userService.findByUserId(anyInt())).thenReturn(new User());
        when(orderService.addOrderlistByUnamePrice(anyString(), anyDouble())).thenReturn(1);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals("200", result.get("status"));
    }

    @Test
    void buyByIdWhenCartIdIsZeroCartIsEmpty() {
        Integer cartId = 0;
        int userId = 1;
        List<Book> bookData = new ArrayList<>();
        List<Cart> cartData = new ArrayList<>();

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals("Cart is empty", result.get("status"));
    }

    @Test
    void buyByIdWhenCartIdIsZeroBookIsNotInStock() {
        Integer cartId = 0;
        int userId = 1;

        List<Book> bookData = new ArrayList<>();
        Book book = new Book();
        book.setId(1);
        book.setIsbn("testIsbn");
        book.setName("testBook");
        book.setType("testType");
        book.setAuthor("testAuthor");
        book.setPrice(BigDecimal.valueOf(100.0));
        book.setDescription("testDescription");
        book.setInventory(0);
        book.setImage("testImage");
        bookData.add(book);

        List<Cart> cartData = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(0);
        cart.setUserId(1);
        cart.setBookId(1);
        cart.setBookNum(1);
        cartData.add(cart);

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);
        when(userService.findByUserId(anyInt())).thenReturn(new User());
        when(orderService.addOrderlistByUnamePrice(anyString(), anyDouble())).thenReturn(1);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals(book.getName() + "库存不足", result.get("status"));
    }

    @Test
    void buyByIdWhenCartIdIsZeroBookIsNotFound() {
        Integer cartId = 0;
        int userId = 1;

        List<Book> bookData = new ArrayList<>();
        List<Cart> cartData = new ArrayList<>();
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setBookId(1);
        cart.setBookNum(1);
        cartData.add(cart);

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals("Book not found", result.get("status"));
    }

    @Test
    void buyByIdReturnsSuccess() {
        Integer cartId = 1;
        int userId = 1;

        List<Book> bookData = new ArrayList<>();
        Book book0 = new Book();
        book0.setId(0);
        book0.setIsbn("testIsbn");
        book0.setName("testBook");
        book0.setType("testType");
        book0.setAuthor("testAuthor");
        book0.setPrice(BigDecimal.valueOf(100.0));
        book0.setDescription("testDescription");
        book0.setInventory(10);
        book0.setImage("testImage");
        bookData.add(book0);

        Book book = new Book();
        book.setId(1);
        book.setIsbn("testIsbn");
        book.setName("testBook");
        book.setType("testType");
        book.setAuthor("testAuthor");
        book.setPrice(BigDecimal.valueOf(100.0));
        book.setDescription("testDescription");
        book.setInventory(10);
        book.setImage("testImage");
        bookData.add(book);

        List<Cart> cartData = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1);
        cart.setUserId(userId);
        cart.setBookId(1);
        cart.setBookNum(1);
        cartData.add(cart);

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);
        when(userService.findByUserId(anyInt())).thenReturn(new User());
        when(orderService.addOrderlistByUnamePrice(anyString(), anyDouble())).thenReturn(1);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals("200", result.get("status"));
    }

    @Test
    void buyByIdWhenCartIsEmpty() {
        Integer cartId = 1;
        int userId = 1;
        List<Book> bookData = new ArrayList<>();
        List<Cart> cartData = new ArrayList<>();

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals("Cart is empty", result.get("status"));
    }

    @Test
    void buyByIdWhenBookIsNotInStock() {
        Integer cartId = 1;
        int userId = 1;

        List<Book> bookData = new ArrayList<>();
        Book book = new Book();
        book.setId(1);
        book.setInventory(0);
        bookData.add(book);

        List<Cart> cartData = new ArrayList<>();
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setBookId(1);
        cart.setBookNum(1);
        cartData.add(cart);

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals(book.getName() + "库存不足", result.get("status"));
    }

    @Test
    void buyByIdWhenBookIsNotFound() {
        Integer cartId = 1;
        int userId = 1;
        List<Book> bookData = new ArrayList<>();
        List<Cart> cartData = new ArrayList<>();
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setBookId(1);
        cart.setBookNum(1);
        cartData.add(cart);

        when(bookservice.findAll()).thenReturn(bookData);
        when(cartService.findCartsByUserId(userId)).thenReturn(cartData);

        Map<String,Object> id = new HashMap<>();
        id.put("cartId", cartId);
        id.put("userId", userId);
        Map<String, String> result = orderController.buyById(id);

        assertEquals("Book not found", result.get("status"));
    }
}