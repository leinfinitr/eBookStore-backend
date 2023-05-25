package com.example.ebookstore.service;

import com.example.ebookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findBookById(Integer id);

    List<Book> findAll();
}
