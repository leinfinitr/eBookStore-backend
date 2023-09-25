package com.example.ebookstore.serviceimpl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.dao.OrderItemDao;
import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Orderlist> findOrderlistsByUserName(String userName) {
        return orderDao.findOrderslistsByUserName(userName);
    }

    @Override
    public List<Orderlist> findOrderlists() {
        return orderDao.findOrderlists();
    }

    @Override
    @Transactional
    public Integer addOrderlistByUnamePrice(String userName, Double price) {
        User user = userDao.findByUserName(userName);
        // 创建新的订单
        Orderlist orderlist = new Orderlist();
        orderlist.setUserName(user.getName());
        orderlist.setPhone(user.getPhone());
        orderlist.setNation(user.getNation());
        orderlist.setProvince(user.getProvince());
        orderlist.setAddress(user.getAddress());
        orderlist.setTotalPrice(BigDecimal.valueOf(price));
        orderlist.setTime(new Timestamp(System.currentTimeMillis()));
        orderDao.saveOrderlist(orderlist);
        return orderlist.getOrderId();
    }

    @Override
    public List<Orderitem> findOrderitemsByOrderId(Integer orderId) {
        return orderItemDao.findOrderitemsByOrderId(orderId);
    }

    @Override
    @Transactional
    public Map<String, Object> addOrder(String orderString) {
        JSONObject order = JSON.parseObject(orderString);
        String userName = (String) order.get("userName");
        Double totalPrice = Double.valueOf(String.valueOf(order.get("totalPrice")));
        Integer orderlistId = addOrderlistByUnamePrice(userName, totalPrice);

        // 创建新的订单项
        List<Map<String, Object>> orderlistItems = (List<Map<String, Object>>) order.get("orderlist");
        for (Map<String, Object> orderlistItem : orderlistItems) {
            addOrderitem(orderlistItem, orderlistId);
        }

        // 返回购买成功
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        return result;
    }

    @Override
    public List<Orderitem> findOrderitems() {
        return orderItemDao.findOrderitems();
    }

    @Override
    @Transactional
    public void addOrderitem(Map<String, Object> map, Integer orderId) {
        Orderitem orderitem = new Orderitem();
        orderitem.setOrderId(orderId);
        orderitem.setName((String) map.get("bookName"));
        orderitem.setPrice((BigDecimal) map.get("bookPrice"));
        orderitem.setImage((String) map.get("bookImage"));
        orderitem.setNum((Integer) map.get("bookNum"));
        orderitem.setPay((BigDecimal) map.get("pay"));

        // 将 orderTime 设置为当前时间
        orderitem.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem.setStatus("待发货");
        orderItemDao.saveOrderitem(orderitem);

        // 更新 book 表中的库存
        Integer bookId = Integer.valueOf(String.valueOf(map.get("bookId")));
        Book book = bookDao.findBookById(bookId).get();
        book.setInventory(book.getInventory() - orderitem.getNum());
        bookDao.save(book);
    }

    @Override
    public List<Orderitem> findOrderitemsByUserName(String name) {
        List<Orderlist> orderlists = orderDao.findOrderslistsByUserName(name);
        List<Orderitem> res = null;
        for (Orderlist orderlist : orderlists) {
            List<Orderitem> orderitems = orderItemDao.findOrderitemsByOrderId(orderlist.getOrderId());
            if (res == null) {
                res = orderitems;
            } else {
                res.addAll(orderitems);
            }
        }
        return res;
    }
}
