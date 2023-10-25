package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // 通过书名查询作者
    @GetMapping("/getAuthorByName")
    public String getAuthorByName(@RequestParam String name) {
        Book book = bookRepository.findByName(name);
        return book.getAuthor();
    }
}