package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Orderlist> findOrderlistsByUserName(String userName) {
        return orderDao.findOrderslistsByUserName(userName);
    }

    @Override
    public List<Orderitem> findOrderitemsByOrderId(Integer orderId) {
        return orderDao.findOrderitemsByOrderId(orderId);
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
        // 将 totalPrice 从 Double 转换为 BigDecimal
        orderlist.setTotalPrice(BigDecimal.valueOf(price));
        orderDao.save(orderlist);
        return orderlist.getOrderId();
    }

    @Override
    public void addOrderitemByOidBidBnumPay(Integer orderId, Integer bookId, Integer bookNum, Double pay) {
        Orderitem orderitem = new Orderitem();
        orderitem.setOrderId(orderId);
        orderitem.setBookId(bookId);
        orderitem.setBookNum(bookNum);
        orderitem.setPay(BigDecimal.valueOf(pay));

        // 将 orderTime 设置为当前时间
        orderitem.setOrderTime(new Timestamp(System.currentTimeMillis()));
        orderitem.setOrderStatus("待发货");
        orderDao.save(orderitem);
    }
}
