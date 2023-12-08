package com.example.ebookstore.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.entity.Book;
import com.example.ebookstore.entity.Labelmap;
import com.example.ebookstore.repository.BookRepository;
import com.example.ebookstore.repository.LabelmapRepository;
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
    private LabelmapRepository labelmapRepository;
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

    @Override
    public List<String> findSimilarLabelByName(String label) {
        List<Labelmap> labelmaps = labelmapRepository.findSimilarLabelByName(label);
        List<String> names = new java.util.ArrayList<>();
        for (Labelmap labelmap : labelmaps) {
            names.add(labelmap.getName());
        }
        return names;
    }

    @Override
    public List<Book> findBookByLabel(List<String> labels) {
        List<Book> books = new java.util.ArrayList<>();
        for (String label : labels) {
            List<Book> bookList = bookRepository.findBooksByType(label);
            books.addAll(bookList);
        }
        return books;
    }

    @Override
    public void initLabelMap() {
        labelmapRepository.deleteAll();

        Labelmap all = new Labelmap("所有书籍");

        Labelmap practical = new Labelmap("实用类");
        Labelmap programming = new Labelmap("编程");
        Labelmap primary = new Labelmap("中小学教辅");

        Labelmap literature = new Labelmap("文学类");
        Labelmap children = new Labelmap("儿童文学");
        Labelmap youth = new Labelmap("青春文学");
        Labelmap biography = new Labelmap("传记文学");
        Labelmap world = new Labelmap("世界名著");
        Labelmap chinese = new Labelmap("中国古典文学");
        Labelmap ancient = new Labelmap("古籍");

        Labelmap humanities = new Labelmap("人文社科类");
        Labelmap novel = new Labelmap("小说");
        Labelmap magic = new Labelmap("魔幻小说");
        Labelmap science = new Labelmap("科幻小说");
        Labelmap society = new Labelmap("社会小说");
        Labelmap martial = new Labelmap("武侠小说");
        Labelmap suspense = new Labelmap("悬疑/推理小说");

        practical.isSimilar(all);
        programming.isSimilar(practical);
        primary.isSimilar(practical);

        literature.isSimilar(all);
        children.isSimilar(literature);
        youth.isSimilar(literature);
        biography.isSimilar(literature);
        world.isSimilar(literature);
        chinese.isSimilar(literature);
        ancient.isSimilar(literature);

        humanities.isSimilar(all);
        novel.isSimilar(humanities);
        magic.isSimilar(humanities);
        science.isSimilar(humanities);
        society.isSimilar(humanities);
        martial.isSimilar(humanities);
        suspense.isSimilar(humanities);

        labelmapRepository.save(all);

        labelmapRepository.save(practical);
        labelmapRepository.save(programming);
        labelmapRepository.save(primary);

        labelmapRepository.save(literature);
        labelmapRepository.save(children);
        labelmapRepository.save(youth);
        labelmapRepository.save(biography);
        labelmapRepository.save(world);
        labelmapRepository.save(chinese);
        labelmapRepository.save(ancient);

        labelmapRepository.save(humanities);
        labelmapRepository.save(novel);
        labelmapRepository.save(magic);
        labelmapRepository.save(science);
        labelmapRepository.save(society);
        labelmapRepository.save(martial);
        labelmapRepository.save(suspense);
    }

    @Override
    public Book findBookByName(String name) {
        return bookRepository.findBookByName(name);
    }
}
