package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
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
        orderDao.save(orderlist);
        return orderlist.getOrderId();
    }

    @Override
    public List<Orderitem> findOrderitemsByOrderId(Integer orderId) {
        return orderDao.findOrderitemsByOrderId(orderId);
    }

    @Override
    public List<Orderitem> findOrderitems() {
        return orderDao.findOrderitems();
    }

    @Override
    public void addOrderitem(Map<String, Object> map, Integer orderId) {
        Orderitem orderitem = new Orderitem();
        orderitem.setOrderId(orderId);
        orderitem.setName((String) map.get("bookName"));
        Object priceObj = map.get("bookPrice");
        if (priceObj instanceof Integer) {
            orderitem.setPrice(BigDecimal.valueOf(((Integer) priceObj).doubleValue()));
        } else if (priceObj instanceof Double) {
            orderitem.setPrice(BigDecimal.valueOf((Double) priceObj));
        }
        orderitem.setImage((String) map.get("bookImage"));
        orderitem.setNum((Integer) map.get("bookNum"));
        Object payObj = map.get("pay");
        if (payObj instanceof Integer) {
            orderitem.setPay(BigDecimal.valueOf(((Integer) payObj).doubleValue()));
        } else if (payObj instanceof Double) {
            orderitem.setPay(BigDecimal.valueOf((Double) payObj));
        }

        // 将 orderTime 设置为当前时间
        orderitem.setTime(new Timestamp(System.currentTimeMillis()));
        orderitem.setStatus("待发货");
        orderDao.save(orderitem);

        // 更新 book 表中的库存
        Integer bookId = (Integer) map.get("bookId");
        Book book = bookDao.findBookById(bookId).get();
        book.setInventory(book.getInventory() - orderitem.getNum());
        bookDao.save(book);
    }

    @Override
    public void deleteOrderlistByOrderId(Integer orderlistId) {
        orderDao.deleteOrderlistByOrderId(orderlistId);
    }

    @Override
    public List<Orderitem> findOrderitemsByUserName(String name) {
        List<Orderlist> orderlists = orderDao.findOrderslistsByUserName(name);
        List<Orderitem> res = null;
        for (Orderlist orderlist : orderlists) {
            List<Orderitem> orderitems = orderDao.findOrderitemsByOrderId(orderlist.getOrderId());
            if (res == null) {
                res = orderitems;
            } else {
                res.addAll(orderitems);
            }
        }
        return res;
    }
}
