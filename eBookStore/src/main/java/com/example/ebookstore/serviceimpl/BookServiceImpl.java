package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    @Override
    public Book findBookByName(String name) {
        return bookDao.findBookByName(name);
    }

    @Override
    public Integer wordCount(String keyword) {
        String sparkSubmitCommand = "D:\\Program\\spark-3.5.0\\bin\\spark-submit --class com.example.spark.WordCount --master spark://192.168.31.87:7077  D:\\course\\ApplicationSystemArchitecture\\eBookStore\\eBookStore-backend\\spark\\target\\spark-1.0-SNAPSHOT.jar " + keyword;
        String outputFilePath = "D:\\course\\ApplicationSystemArchitecture\\eBookStore\\eBookStore-backend\\WordCountOutput\\" + keyword + ".txt";

        try {
            // 在调用 Java 代码时，Runtime.exec() 方法执行的命令进程由于缓冲区大小限制，导致无法获取即时的输出结果。
            // 这是由于 Spark 在运行过程中会生成大量的输出，而缓冲区的限制导致输出被阻塞，最终导致进程无法正常结束。
            // 解决方案是使用 ProcessBuilder 类来调用 "spark-submit" 命令，并通过重定向输入、输出和错误流来处理结果。这样可以避免缓冲区大小限制导致的阻塞问题。
            // Process process = Runtime.getRuntime().exec("cmd /c " + sparkSubmitCommand);
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", sparkSubmitCommand);
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(outputFilePath)));
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFilePath))) {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(result);
    }
}
