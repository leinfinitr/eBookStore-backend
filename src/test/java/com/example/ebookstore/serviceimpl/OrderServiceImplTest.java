package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private UserDao userDao;

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findOrderlistsByUserName() {
        String userName = "testUser";
        List<Orderlist> expectedOrderlists = new ArrayList<>();
        when(orderDao.findOrderslistsByUserName(userName)).thenReturn(expectedOrderlists);

        List<Orderlist> actualOrderlists = orderServiceImpl.findOrderlistsByUserName(userName);

        assertEquals(expectedOrderlists, actualOrderlists);
    }

    @Test
    void findOrderlists() {
        List<Orderlist> expectedOrderlists = new ArrayList<>();
        when(orderDao.findOrderlists()).thenReturn(expectedOrderlists);

        List<Orderlist> actualOrderlists = orderServiceImpl.findOrderlists();

        assertEquals(expectedOrderlists, actualOrderlists);
    }

    @Test
    void addOrderlistByUnamePrice() {
        String userName = "testUser";
        Double price = 100.0;
        User user = new User();
        user.setName(userName);
        when(userDao.findByUserName(userName)).thenReturn(user);

        Orderlist expectedOrderlist = new Orderlist();
        expectedOrderlist.setUserName(userName);
        expectedOrderlist.setTotalPrice(BigDecimal.valueOf(price));
        when(orderDao.save(any(Orderlist.class))).thenReturn(expectedOrderlist);

        Integer actualOrderId = orderServiceImpl.addOrderlistByUnamePrice(userName, price);

        assertEquals(expectedOrderlist.getOrderId(), actualOrderId);
    }

    @Test
    void findOrderitemsByOrderId() {
        Integer orderId = 1;
        List<Orderitem> expectedOrderitems = new ArrayList<>();
        when(orderDao.findOrderitemsByOrderId(orderId)).thenReturn(expectedOrderitems);

        List<Orderitem> actualOrderitems = orderServiceImpl.findOrderitemsByOrderId(orderId);

        assertEquals(expectedOrderitems, actualOrderitems);
    }

    @Test
    void findOrderitems() {
        List<Orderitem> expectedOrderitems = new ArrayList<>();
        when(orderDao.findOrderitems()).thenReturn(expectedOrderitems);

        List<Orderitem> actualOrderitems = orderServiceImpl.findOrderitems();

        assertEquals(expectedOrderitems, actualOrderitems);
    }

    @Test
    void addOrderitemSuccessfully() {
        Integer orderId = 1;
        Map<String, Object> map = new HashMap<>();
        map.put("bookName", "testBook");
        map.put("bookPrice", 100.0);
        map.put("bookImage", "testImage");
        map.put("bookNum", 1);
        map.put("pay", 100.0);
        map.put("bookId", 1);

        Book book = new Book();
        book.setInventory(10);
        when(bookDao.findBookById((Integer) map.get("bookId"))).thenReturn(Optional.of(book));

        Orderitem expectedOrderitem = new Orderitem();
        expectedOrderitem.setOrderId(orderId);
        expectedOrderitem.setName((String) map.get("bookName"));
        expectedOrderitem.setPrice(BigDecimal.valueOf((Double) map.get("bookPrice")));
        expectedOrderitem.setImage((String) map.get("bookImage"));
        expectedOrderitem.setNum((Integer) map.get("bookNum"));
        expectedOrderitem.setPay(BigDecimal.valueOf((Double) map.get("pay")));
        when(orderDao.save(any(Orderitem.class))).thenReturn(expectedOrderitem);

        orderServiceImpl.addOrderitem(map, orderId);

        verify(orderDao).save(any(Orderitem.class));
        verify(bookDao).save(any(Book.class));
    }

    @Test
    void addOrderitemSuccessfullyWithIntegerPrice() {
        Integer orderId = 1;
        Map<String, Object> map = new HashMap<>();
        map.put("bookName", "testBook");
        map.put("bookPrice", 100);
        map.put("bookImage", "testImage");
        map.put("bookNum", 1);
        map.put("pay", 100);
        map.put("bookId", 1);

        Book book = new Book();
        book.setInventory(10);
        when(bookDao.findBookById((Integer) map.get("bookId"))).thenReturn(Optional.of(book));

        Orderitem expectedOrderitem = new Orderitem();
        expectedOrderitem.setOrderId(orderId);
        expectedOrderitem.setName((String) map.get("bookName"));
        expectedOrderitem.setPrice(BigDecimal.valueOf((Integer) map.get("bookPrice")));
        expectedOrderitem.setImage((String) map.get("bookImage"));
        expectedOrderitem.setNum((Integer) map.get("bookNum"));
        expectedOrderitem.setPay(BigDecimal.valueOf((Integer) map.get("pay")));
        when(orderDao.save(any(Orderitem.class))).thenReturn(expectedOrderitem);

        orderServiceImpl.addOrderitem(map, orderId);

        verify(orderDao).save(any(Orderitem.class));
        verify(bookDao).save(any(Book.class));
    }

    @Test
    void addOrderitemSuccessfullyWithStringPrice() {
        Integer orderId = 1;
        Map<String, Object> map = new HashMap<>();
        map.put("bookName", "testBook");
        map.put("bookPrice", "100.0");
        map.put("bookImage", "testImage");
        map.put("bookNum", 1);
        map.put("pay", "100.0");
        map.put("bookId", 1);

        Book book = new Book();
        book.setInventory(10);
        when(bookDao.findBookById((Integer) map.get("bookId"))).thenReturn(Optional.of(book));

        Orderitem expectedOrderitem = new Orderitem();
        expectedOrderitem.setOrderId(orderId);
        expectedOrderitem.setName((String) map.get("bookName"));
        expectedOrderitem.setPrice(BigDecimal.valueOf(Double.parseDouble((String) map.get("bookPrice"))));
        expectedOrderitem.setImage((String) map.get("bookImage"));
        expectedOrderitem.setNum((Integer) map.get("bookNum"));
        expectedOrderitem.setPay(BigDecimal.valueOf(Double.parseDouble((String) map.get("pay"))));
        when(orderDao.save(any(Orderitem.class))).thenReturn(expectedOrderitem);

        orderServiceImpl.addOrderitem(map, orderId);

        verify(orderDao).save(any(Orderitem.class));
        verify(bookDao).save(any(Book.class));
    }

    @Test
    void deleteOrderlistByOrderId() {
        Integer orderlistId = 1;
        doNothing().when(orderDao).deleteOrderlistByOrderId(orderlistId);

        orderServiceImpl.deleteOrderlistByOrderId(orderlistId);

        verify(orderDao).deleteOrderlistByOrderId(orderlistId);
    }

    @Test
    void findOrderitemsByUserName() {
        String userName = "testUser";
        List<Orderlist> orderlists = new ArrayList<>();
        Orderlist orderlist1 = new Orderlist();
        Orderlist orderlist2 = new Orderlist();
        orderlist1.setOrderId(1);
        orderlist2.setOrderId(2);
        orderlists.add(orderlist1);
        orderlists.add(orderlist2);
        when(orderDao.findOrderslistsByUserName(userName)).thenReturn(orderlists);

        List<Orderitem> expectedOrderitems = new ArrayList<>();
        Orderitem orderitem1 = new Orderitem();
        Orderitem orderitem2 = new Orderitem();
        orderitem1.setOrderId(1);
        orderitem2.setOrderId(2);
        expectedOrderitems.add(orderitem1);
        expectedOrderitems.add(orderitem2);
        when(orderDao.findOrderitemsByOrderId(1)).thenReturn(new ArrayList<>(Collections.singletonList(orderitem1)));
        when(orderDao.findOrderitemsByOrderId(2)).thenReturn(new ArrayList<>(Collections.singletonList(orderitem2)));
        List<Orderitem> actualOrderitems = orderServiceImpl.findOrderitemsByUserName(userName);

        assertEquals(expectedOrderitems, actualOrderitems);
    }
}