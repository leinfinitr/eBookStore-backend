package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
