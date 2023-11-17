package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    private Boolean isLableMapInit = false;

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookDao.findBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Map<String, Object> addBook(Map<String, String> params) {
        String isbn = params.get("isbn");
        String name = params.get("name");
        String author = params.get("author");
        String type = params.get("type");
        String price = params.get("price");
        String inventory = params.get("inventory");
        String description = params.get("description");
        String image = params.get("image");

        Book book = new Book();
        book.setIsbn(isbn);
        book.setName(name);
        book.setAuthor(author);
        book.setType(type);
        book.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        book.setInventory(Integer.parseInt(inventory));
        book.setDescription(description);
        book.setImage(image);

        bookDao.save(book);
        return Map.of("status", 200);
    }

    @Override
    public Map<String, Object> updateBook(Map<String, String> params) {
        String id = params.get("id");
        String isbn = params.get("isbn");
        String name = params.get("name");
        String author = params.get("author");
        String type = params.get("type");
        String price = params.get("price");
        String inventory = params.get("inventory");
        String description = params.get("description");
        String image = params.get("image");

        Optional<Book> book = bookDao.findBookById(Integer.parseInt(id));
        book.ifPresent(value -> {
            value.setIsbn(isbn);
            value.setName(name);
            value.setAuthor(author);
            value.setType(type);
            value.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
            value.setInventory(Integer.parseInt(inventory));
            value.setDescription(description);
            value.setImage(image);
        });

        bookDao.save(book.get());
        return Map.of("status", 200);
    }

    @Override
    public Map<String, Object> deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
        return Map.of("status", 200);
    }

    @Override
    public List<Book> searchBookByLabel(String label) {
        // 查询与该标签相近的所有标签
        if (!isLableMapInit) {
            bookDao.initLabelMap();
            isLableMapInit = true;
        }
        List<String> labels = bookDao.findSimilarLabelByName(label);
        // 查询与这些标签相近的所有书籍
        return bookDao.findBookByLabel(labels);
    }
}
