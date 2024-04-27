package com.example.ebookstore.controller;

import com.example.ebookstore.entity.*;
import com.example.ebookstore.service.BookService;
import com.example.ebookstore.service.CartService;
import com.example.ebookstore.service.OrderService;
import com.example.ebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    BookService bookservice;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

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
    public Map<String, Object> buy(@RequestBody Map<String, Object> order) {
        String userName = (String) order.get("userName");
        Double totalPrice = null;
        Object payObj = order.get("totalPrice");
        if (payObj instanceof Integer) {
            totalPrice = ((Integer) payObj).doubleValue();
        } else if (payObj instanceof Double) {
            totalPrice = (Double) payObj;
        }
        Integer orderlistId = orderService.addOrderlistByUnamePrice(userName, totalPrice);

        // 创建新的订单项
        List<Map<String, Object>> orderlistItems = (List<Map<String, Object>>) order.get("orderlist");
        for (Map<String, Object> orderlistItem : orderlistItems) {
            orderService.addOrderitem(orderlistItem, orderlistId);
        }

        // 返回购买成功
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        return result;
    }

    @GetMapping("/buyById")
    public Map<String, String> buyById(@RequestParam(value = "cartId") Integer cartId) {
        List<Book> bookData = bookservice.findAll();
        List<Cart> cartData = cartService.findCartsByUserId(cartId);
        if (cartData.isEmpty()) {
            Map<String, String> result = new HashMap<>();
            result.put("status", "Cart is empty");
            return result;
        }
        int userId = cartData.get(0).getUserId();
        double pay;        // 每个订单项购买的总价
        double totalPay = 0.0;   // 订单的总价
        List<Orderitem> orderList = new ArrayList<>(); // 订单中的书籍列表
        List<Integer> bookIdList = new ArrayList<>();  // 书籍 id 列表
        // 如果 id 为 0，表示购买全部书籍
        if (cartId == 0) {
            for (Cart cartBuy : cartData) {
                // 在 bookData 中查找书籍信息
                for (Book book : bookData) {
                    if (book.getId() == cartBuy.getBookId()) {
                        // 如果书籍库存量不足，则购买失败并提示
                        if (cartBuy.getBookNum() > book.getInventory()) {
                            Map<String, String> result = new HashMap<>();
                            result.put("status", book.getName() + "库存不足");
                            return result;
                        }
                        // 根据 amount 和 price 计算总价
                        pay = cartBuy.getBookNum() * Double.parseDouble(String.valueOf(book.getPrice()));
                        totalPay += pay;
                        Orderitem orderItem = new Orderitem();
                        orderItem.setName(book.getName());
                        orderItem.setPrice(book.getPrice());
                        orderItem.setImage(book.getImage());
                        orderItem.setNum(cartBuy.getBookNum());
                        orderItem.setPay(BigDecimal.valueOf(pay));

                        orderList.add(orderItem);
                        bookIdList.add(book.getId());
                        break;
                    }
                }
                if (orderList.isEmpty()) {
                    Map<String, String> result = new HashMap<>();
                    result.put("status", "Book not found");
                    return result;
                }
            }
        } else {
            for (Cart cartBuy : cartData) {
                // 在 bookData 中查找书籍信息
                for (Book book : bookData) {
                    if (book.getId() == cartBuy.getBookId()) {
                        // 如果书籍库存量不足，则购买失败并提示
                        if (cartBuy.getBookNum() > book.getInventory()) {
                            Map<String, String> result = new HashMap<>();
                            result.put("status", book.getName() + "库存不足");
                            return result;
                        }
                        // 根据 amount 和 price 计算总价
                        pay = cartBuy.getBookNum() * Double.parseDouble(String.valueOf(book.getPrice()));
                        totalPay += pay;
                        Orderitem orderItem = new Orderitem();
                        orderItem.setName(book.getName());
                        orderItem.setPrice(book.getPrice());
                        orderItem.setImage(book.getImage());
                        orderItem.setNum(cartBuy.getBookNum());
                        orderItem.setPay(BigDecimal.valueOf(pay));

                        orderList.add(orderItem);
                        bookIdList.add(book.getId());
                        break;
                    }
                }
                if (!orderList.isEmpty()) {
                    // 由于一次只够买一本书，所以找到书籍后就可以跳出循环
                    break;
                }
            }
            if (orderList.isEmpty()) {
                Map<String, String> result = new HashMap<>();
                result.put("status", "Book not found");
                return result;
            }
        }

        if (orderList.size() != bookIdList.size()) {
            Map<String, String> result = new HashMap<>();
            result.put("status", "Unexpected error");
            return result;
        }

        User user = userService.findByUserId(userId);

        Integer orderlistId = orderService.addOrderlistByUnamePrice(user.getName(), totalPay);
        for (Orderitem orderlistItem : orderList) {
            Map<String, Object> map = new HashMap<>();
            map.put("bookName", orderlistItem.getName());
            map.put("bookPrice", orderlistItem.getPrice());
            map.put("bookImage", orderlistItem.getImage());
            map.put("bookNum", orderlistItem.getNum());
            map.put("pay", orderlistItem.getPay());
            map.put("bookId", bookIdList.get(orderList.indexOf(orderlistItem)));
            orderService.addOrderitem(map, orderlistId);
        }

        // 点击从购物车中删除书籍
        for (Integer bookId : bookIdList) {
            cartService.deleteByUserIdAndBookId(userId, bookId);
        }

        Map<String, String> result = new HashMap<>();
        result.put("status", "200");
        return result;
    }
}
