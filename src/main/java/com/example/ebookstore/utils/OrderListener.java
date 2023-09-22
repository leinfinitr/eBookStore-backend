package com.example.ebookstore.utils;

import com.example.ebookstore.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderListener {
    @Autowired
    OrderService orderService;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public static Map<String, Object> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, Object> map = new HashMap<String, Object>();
        for (String string : strs) {
            String key = string.split("=")[0];
            String value = string.split("=")[1];
            // 去掉头部空格
            String key1 = key.trim();
            String value1 = value.trim();
            map.put(key1, value1);
        }
        return map;
    }

    @KafkaListener(topics = "order", groupId = "order")
    public void orderListener(ConsumerRecord<String, String> record) {
        Map<String, Object> orderlistItem = mapStringToMap(record.value());
        int orderlistId = Integer.parseInt(record.key());
        System.out.println("接收到订单信息：" + orderlistId + " " + orderlistItem);
        orderService.addOrderitem(orderlistItem, orderlistId);
        System.out.println("改写订单数据成功");
        kafkaTemplate.send("result", "success");
    }

    @KafkaListener(topics = "result", groupId = "order")
    public void resultListener(ConsumerRecord<String, String> record) {
        System.out.println("接收到处理结果: " + record.value());
    }
}
