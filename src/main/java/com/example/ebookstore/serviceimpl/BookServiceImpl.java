package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookDao.findBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
