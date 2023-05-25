package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findById(int id);
}
