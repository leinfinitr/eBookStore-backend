package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Cart;
import com.example.ebookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping("/getCartByUserId")
    public List<Cart> getCartByUserId(@RequestParam(value = "userId") String userId) {
        return cartService.findCartsByUserId(Integer.parseInt(userId));
    }

    @PostMapping("/addCart")
    public Cart addCart(@RequestBody Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        int bookId = Integer.parseInt(params.get("bookId"));
        int bookNum = Integer.parseInt(params.get("bookNum"));
        return cartService.addByUidBidBnum(userId, bookId, bookNum);
    }

    @PostMapping("/deleteCart")
    public void deleteCart(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        String bookId = params.get("bookId");
        cartService.deleteByUserIdAndBookId(Integer.parseInt(userId), Integer.parseInt(bookId));
    }

    @PostMapping("/modifyCart")
    public Cart modifyCart(@RequestBody Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        int bookId = Integer.parseInt(params.get("bookId"));
        int bookNum = Integer.parseInt(params.get("bookNum"));
        return cartService.modifyByUidBidBnum(userId, bookId, bookNum);
    }
}
