package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Integer> {
    List<Book> findBooksByType(String label);

    Book findBookByName(String name);
}
