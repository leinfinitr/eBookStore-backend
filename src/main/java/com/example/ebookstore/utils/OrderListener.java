package com.example.ebookstore.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.ebookstore.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    OrderService orderService;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    WebSocketServer ws;

    @KafkaListener(topics = "order", groupId = "order")
    public void orderListener(ConsumerRecord<String, String> record) {
        orderService.addOrder(record.value());
        JSONObject order = JSON.parseObject(record.value());
        String userName = (String) order.get("userName");
        kafkaTemplate.send("result", userName, "购买成功");
    }

    @KafkaListener(topics = "result", groupId = "order")
    public void resultListener(ConsumerRecord<String, String> record) {
        System.out.println("接收到处理结果: " + record.key() + " " + record.value());
        ws.sendMessageToUser(record.key(), record.value());
    }
}
