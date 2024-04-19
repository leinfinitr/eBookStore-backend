package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Book;
import com.example.ebookstore.serviceimpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookControllerTest {
    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBookByIdReturnsBookWhenIdExists() {
        String id = "1";
        Book expectedBook = new Book();

        expectedBook.setId(Integer.parseInt(id));
        expectedBook.setIsbn("1");
        expectedBook.setName("Java核心技术卷II");
        expectedBook.setType("编程");
        expectedBook.setAuthor("凯S.霍斯特曼");
        expectedBook.setPrice(BigDecimal.valueOf(95.20));
        expectedBook.setDescription("本书是Java领域有影响力和价值的著作之一，由拥有20多年教学与研究经验的Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。第10版根据JavaSE8全面更新，同时修正了第9版中的不足，系统全面讲解了Java语言的核心概念、语法、重要特性和开发方法，包含大量案例，实践性强。");
        expectedBook.setInventory(4);
        expectedBook.setImage("http://img3m9.ddimg.cn/12/36/1546133799-1_w_1.jpg");

        when(bookService.findBookById(Integer.parseInt(id))).thenReturn(Optional.of(expectedBook));

        Optional<Book> actualBook = bookController.getBookById(id);

        assertEquals(Optional.of(expectedBook), actualBook);
    }

    @Test
    void getBookByIdReturnsEmptyWhenIdDoesNotExist() {
        String id = "1";

        when(bookService.findBookById(Integer.parseInt(id))).thenReturn(Optional.empty());

        Optional<Book> actualBook = bookController.getBookById(id);

        assertEquals(Optional.empty(), actualBook);
    }

    @Test
    void getAllBooks() {
        Book expectedBook = new Book();

        expectedBook.setId(1);
        expectedBook.setIsbn("1");
        expectedBook.setName("Java核心技术卷II");
        expectedBook.setType("编程");
        expectedBook.setAuthor("凯S.霍斯特曼");
        expectedBook.setPrice(BigDecimal.valueOf(95.20));
        expectedBook.setDescription("本书是Java领域有影响力和价值的著作之一，由拥有20多年教学与研究经验的Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。第10版根据JavaSE8全面更新，同时修正了第9版中的不足，系统全面讲解了Java语言的核心概念、语法、重要特性和开发方法，包含大量案例，实践性强。");
        expectedBook.setInventory(4);
        expectedBook.setImage("http://img3m9.ddimg.cn/12/36/1546133799-1_w_1.jpg");

        when(bookService.findAll()).thenReturn(List.of(expectedBook));

        List<Book> actualBook = bookController.getAllBooks();

        assertEquals(List.of(expectedBook), actualBook);
    }

    @Test
    void addBook() {
        Map<String, String> params = Map.of(
                "isbn", "1",
                "name", "Java核心技术卷II",
                "type", "编程",
                "author", "凯S.霍斯特曼",
                "price", "95.20",
                "description", "本书是Java领域有影响力和价值的著作之一，由拥有20多年教学与研究经验的Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。第10版根据JavaSE8全面更新，同时修正了第9版中的不足，系统全面讲解了Java语言的核心概念、语法、重要特性和开发方法，包含大量案例，实践性强。",
                "inventory", "4",
                "image", "http://img3m9.ddimg.cn/12/36/1546133799-1_w_1.jpg"
        );

        when(bookService.addBook(params)).thenReturn(Map.of("status", 200));
        Map<String, Object> result = bookController.addBook(params);

        assertEquals(200, result.get("status"));
    }

    @Test
    void updateBook() {
        Map<String, String> params = Map.of(
                "id", "1",
                "isbn", "1",
                "name", "Java核心技术卷II",
                "type", "编程",
                "author", "凯S.霍斯特曼",
                "price", "95.20",
                "description", "本书是Java领域有影响力和价值的著作之一，由拥有20多年教学与研究经验的Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。第10版根据JavaSE8全面更新，同时修正了第9版中的不足，系统全面讲解了Java语言的核心概念、语法、重要特性和开发方法，包含大量案例，实践性强。",
                "inventory", "4",
                "image", "http://img3m9.ddimg.cn/12/36/1546133799-1_w_1.jpg"
        );

        when(bookService.updateBook(params)).thenReturn(Map.of("status", 200));
        Map<String, Object> result = bookController.updateBook(params);

        assertEquals(200, result.get("status"));
    }

    @Test
    void deleteBookSuccessful() {
        Map<String, String> params = Map.of("id", "1");

        when(bookService.deleteBookById(1)).thenReturn(Map.of("status", 200));
        Map<String, Object> result = bookController.deleteBook(params);

        assertEquals(200, result.get("status"));
    }

    @Test
    void deleteBookNoExits() {
        Map<String, String> params = Map.of("id", "1");

        when(bookService.deleteBookById(1)).thenReturn(Map.of("status", 400));
        Map<String, Object> result = bookController.deleteBook(params);

        assertEquals(400, result.get("status"));
    }
}