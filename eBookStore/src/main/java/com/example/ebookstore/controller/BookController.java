package com.example.ebookstore.controller;

import com.example.ebookstore.entity.Book;
import com.example.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService bookService;

    // 通过id获取Book实体信息
    @GetMapping("/bookDetail")
    public Optional<Book> getBookById(@RequestParam(value = "id", defaultValue = "1") String id) {
        return bookService.findBookById(Integer.parseInt(id));
    }

    // 获取所有Book实体信息
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/addBook")
    public Map<String, Object> addBook(@RequestBody Map<String, String> params) {
        return bookService.addBook(params);
    }

    @PostMapping("/updateBook")
    public Map<String, Object> updateBook(@RequestBody Map<String, String> params) {
        return bookService.updateBook(params);
    }

    @PostMapping("/deleteBook")
    public Map<String, Object> deleteBook(@RequestBody Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        return bookService.deleteBookById(id);
    }

    // 通过书的标签搜索与该书标签相似的书籍
    @GetMapping("/searchBookByLabel")
    public List<Book> searchBookByLabel(@RequestParam(value = "name") String name) {
        return bookService.searchBookByLabel(name);
    }

    // 运行 spark-submit 命令统计某个关键词出现次数
    @GetMapping("/wordCount")
    public Integer wordCount(@RequestParam(value = "keyword") String keyword) {
        return bookService.wordCount(keyword);
    }
}
