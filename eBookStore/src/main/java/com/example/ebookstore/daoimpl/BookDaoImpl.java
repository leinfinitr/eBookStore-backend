package com.example.ebookstore.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Optional<Book> findBookById(Integer id) {
        Book book = null;
        System.out.println("Searching book by id: " + id + " in redis...");
        String result = (String) redisTemplate.opsForValue().get("book_" + id);

        if (result == null) {
            System.out.println("Book not found in redis, searching in database...");
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                book = bookOptional.get();
                System.out.println("Book found in database, adding to redis...");
                redisTemplate.opsForValue().set("book_" + id, JSON.toJSONString(book));
            }
        } else {
            System.out.println("Book found in redis.");
            book = JSON.parseObject(result, Book.class);
        }
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
        redisTemplate.opsForValue().set("book_" + book.getId(), JSON.toJSONString(book));
        System.out.println("Book " + book.getId() + " saved in redis.");
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
        redisTemplate.delete("book_" + id);
        System.out.println("Book " + id + " deleted in redis.");
    }

}
