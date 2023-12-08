package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Book;
import com.example.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookGraphqlController {
    @Autowired
    private BookService bookService;

    // 按照书籍名称来查找书籍
    @QueryMapping
    public Book bookByName(@Argument String name) {
        return bookService.findBookByName(name);
    }
}
