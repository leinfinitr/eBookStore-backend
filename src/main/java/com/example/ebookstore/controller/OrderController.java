package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Orderitem;
import com.example.ebookstore.entity.Orderlist;
import com.example.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/getOrdersByUserName")
    public List<Orderlist> getOrders(@RequestParam(value = "name") String name) {
        return orderService.findOrderlistsByUserName(name);
    }

    @GetMapping("/getOrders")
    public List<Orderlist> getOrders() {
        return orderService.findOrderlists();
    }

    @GetMapping("/getOrderItemsByUserName")
    public List<Orderitem> getOrderItemsByUserName(@RequestParam(value = "name") String name) {
        return orderService.findOrderitemsByUserName(name);
    }

    @GetMapping("/getOrderItemsByOrderId")
    public List<Orderitem> getOrderItems(@RequestParam(value = "orderId") Integer orderId) {
        return orderService.findOrderitemsByOrderId(orderId);
    }

    @GetMapping("/getOrderItems")
    public List<Orderitem> getOrderItems() {
        return orderService.findOrderitems();
    }

    // 接收前端发送过来的订单信息，并返回购买成功
    // order:
    // userId: Integer,
    // totalPrice: BigDecimal,
    // orderlist: [orderitem, orderitem, ...]
    @PostMapping("/buy")
    public Map<String, Object> buy(@RequestBody String order) {
        // 创建新的订单项
        kafkaTemplate.send("order", order);

        // 返回购买成功
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        return result;
    }
}
