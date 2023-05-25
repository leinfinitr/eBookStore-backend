package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Book;
import com.example.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    @Autowired
    private BookService bookService;

    // 通过id获取Book实体信息
    @GetMapping("/bookDetail")
    public Optional<Book> getBookById(@RequestParam(value = "id", defaultValue = "1") String id) {
        return bookService.findBookById(Integer.parseInt(id));
    }

    // 获取所有Book实体信息
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
