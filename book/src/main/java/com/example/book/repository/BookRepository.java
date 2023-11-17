package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer> {
    Book findByName(String name);
}
