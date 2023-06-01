package com.example.ebookstore.dao;

import com.example.ebookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findBookById(Integer id);

    List<Book> findAll();

    void save(Book book);

    void deleteBookById(Integer id);
}
