package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/getOrdersByUserName")
    public List<Orderlist> getOrders(@RequestParam(value = "name") String name) {
        return orderService.findOrderlistsByUserName(name);
    }

    // 通过订单号获取订单项
    @GetMapping("/getOrderItemsByOrderId")
    public List<Orderitem> getOrderItems(@RequestParam(value = "orderId") Integer orderId) {
        return orderService.findOrderitemsByOrderId(orderId);
    }

    // 接收前端发送过来的订单信息，并返回购买成功
    // order:
    // userId: Integer,
    // totalPrice: BigDecimal,
    // orderlist: [orderitem, orderitem, ...]
    @PostMapping("/buy")
    public Map<String, Object> buy(@RequestBody Map<String, Object> order) {
        String userName = (String) order.get("userName");
        Double totalPrice = (Double) order.get("totalPrice");
        Integer orderlistId = orderService.addOrderlistByUnamePrice(userName, totalPrice);

        // 创建新的订单项
        List<Map<String, Object>> orderlistItems = (List<Map<String, Object>>) order.get("orderlist");
        for (Map<String, Object> orderlistItem : orderlistItems) {
            Integer bookId = (Integer) orderlistItem.get("bookId");
            Integer bookNum = (Integer) orderlistItem.get("bookNum");
            Double pay = null;
            Object payObj = orderlistItem.get("pay");
            if (payObj instanceof Integer) {
                pay = ((Integer) payObj).doubleValue();
            } else if (payObj instanceof Double) {
                pay = (Double) payObj;
            }
            orderService.addOrderitemByOidBidBnumPay(orderlistId, bookId, bookNum, pay);
        }

        // 返回购买成功
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        return result;
    }
}
