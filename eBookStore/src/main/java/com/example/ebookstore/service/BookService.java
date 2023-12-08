package com.example.ebookstore.service;

import com.example.ebookstore.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
    Optional<Book> findBookById(Integer id);

    List<Book> findAll();

    Map<String, Object> addBook(Map<String, String> params);

    Map<String, Object> updateBook(Map<String, String> params);

    Map<String, Object> deleteBookById(Integer id);

    List<Book> searchBookByLabel(String label);

    Book findBookByName(String name);
}
