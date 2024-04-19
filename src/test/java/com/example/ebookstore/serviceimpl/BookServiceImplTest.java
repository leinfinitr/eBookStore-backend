package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findBookByIdSuccessfully() {
        Book book = new Book();
        book.setId(1);
        book.setIsbn("1234");
        book.setName("Test Book");
        book.setAuthor("Test Author");
        book.setType("Test Type");
        book.setPrice(BigDecimal.valueOf(10.99));
        book.setInventory(100);
        book.setDescription("Test Description");
        book.setImage("Test Image");

        when(bookDao.findBookById(1)).thenReturn(java.util.Optional.of(book));

        assertEquals(book, bookService.findBookById(1).get());
    }

    @Test
    void findAllSuccessfully() {
        bookService.findAll();

        verify(bookDao).findAll();
    }

    @Test
    void addBookSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("isbn", "1234");
        params.put("name", "Test Book");
        params.put("author", "Test Author");
        params.put("type", "Test Type");
        params.put("price", "10.99");
        params.put("inventory", "100");
        params.put("description", "Test Description");
        params.put("image", "Test Image");

        Map<String, Object> result = bookService.addBook(params);

        assertEquals(200, result.get("status"));
    }

    @Test
    void updateBookSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        params.put("isbn", "1234");
        params.put("name", "Test Book");
        params.put("author", "Test Author");
        params.put("type", "Test Type");
        params.put("price", "10.99");
        params.put("inventory", "100");
        params.put("description", "Test Description");
        params.put("image", "Test Image");

        Book book = new Book();
        book.setIsbn(params.get("isbn"));
        book.setName(params.get("name"));
        book.setAuthor(params.get("author"));
        book.setType(params.get("type"));
        book.setPrice(BigDecimal.valueOf(Double.parseDouble(params.get("price"))));
        book.setInventory(Integer.parseInt(params.get("inventory")));
        book.setDescription(params.get("description"));
        book.setImage(params.get("image"));

        when(bookDao.findBookById(1)).thenReturn(java.util.Optional.of(book));

        Map<String, Object> result = bookService.updateBook(params);

        assertEquals(200, result.get("status"));
    }

    @Test
    void updateBookNoExits() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        params.put("isbn", "1234");
        params.put("name", "Test Book");
        params.put("author", "Test Author");
        params.put("type", "Test Type");
        params.put("price", "10.99");
        params.put("inventory", "100");
        params.put("description", "Test Description");
        params.put("image", "Test Image");

        Map<String, Object> result = bookService.updateBook(params);

        assertNotEquals(200, result.get("status"));
    }

    @Test
    void deleteBookSuccessfully() {
        Map<String, Object> result = bookService.deleteBookById(1);

        assertEquals(200, result.get("status"));
    }
}