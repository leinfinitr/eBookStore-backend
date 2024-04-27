package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BookTest {

    @Test
    void getIdReturnsCorrectId() {
        Book book = new Book();
        book.setId(1);
        assertEquals(1, book.getId());
    }

    @Test
    void setIdSetsIdCorrectly() {
        Book book = new Book();
        book.setId(2);
        assertEquals(2, book.getId());
    }

    @Test
    void getIsbnReturnsCorrectIsbn() {
        Book book = new Book();
        book.setIsbn("1234");
        assertEquals("1234", book.getIsbn());
    }

    @Test
    void setIsbnSetsIsbnCorrectly() {
        Book book = new Book();
        book.setIsbn("5678");
        assertEquals("5678", book.getIsbn());
    }

    @Test
    void getNameReturnsCorrectName() {
        Book book = new Book();
        book.setName("Test Book");
        assertEquals("Test Book", book.getName());
    }

    @Test
    void setNameSetsNameCorrectly() {
        Book book = new Book();
        book.setName("New Test Book");
        assertEquals("New Test Book", book.getName());
    }

    @Test
    void getTypeReturnsCorrectType() {
        Book book = new Book();
        book.setType("Test Type");
        assertEquals("Test Type", book.getType());
    }

    @Test
    void setTypeSetsTypeCorrectly() {
        Book book = new Book();
        book.setType("New Test Type");
        assertEquals("New Test Type", book.getType());
    }

    @Test
    void getAuthorReturnsCorrectAuthor() {
        Book book = new Book();
        book.setAuthor("Test Author");
        assertEquals("Test Author", book.getAuthor());
    }

    @Test
    void setAuthorSetsAuthorCorrectly() {
        Book book = new Book();
        book.setAuthor("New Test Author");
        assertEquals("New Test Author", book.getAuthor());
    }

    @Test
    void getPriceReturnsCorrectPrice() {
        Book book = new Book();
        book.setPrice(BigDecimal.valueOf(10.99));
        assertEquals(BigDecimal.valueOf(10.99), book.getPrice());
    }

    @Test
    void setPriceSetsPriceCorrectly() {
        Book book = new Book();
        book.setPrice(BigDecimal.valueOf(20.99));
        assertEquals(BigDecimal.valueOf(20.99), book.getPrice());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        Book book = new Book();
        book.setDescription("Test Description");
        assertEquals("Test Description", book.getDescription());
    }

    @Test
    void setDescriptionSetsDescriptionCorrectly() {
        Book book = new Book();
        book.setDescription("New Test Description");
        assertEquals("New Test Description", book.getDescription());
    }

    @Test
    void getInventoryReturnsCorrectInventory() {
        Book book = new Book();
        book.setInventory(100);
        assertEquals(100, book.getInventory());
    }

    @Test
    void setInventorySetsInventoryCorrectly() {
        Book book = new Book();
        book.setInventory(200);
        assertEquals(200, book.getInventory());
    }

    @Test
    void getImageReturnsCorrectImage() {
        Book book = new Book();
        book.setImage("Test Image");
        assertEquals("Test Image", book.getImage());
    }

    @Test
    void setImageSetsImageCorrectly() {
        Book book = new Book();
        book.setImage("New Test Image");
        assertEquals("New Test Image", book.getImage());
    }

    @Test
    void equalsReturnsTrueForSameBook() {
        Book book1 = new Book();
        book1.setId(1);
        assertEquals(book1, book1);
    }

    @Test
    void equalsReturnsTrueForEqualBook() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setIsbn("1234");
        book1.setName("Test Book");
        book1.setType("Test Type");
        book1.setAuthor("Test Author");
        book1.setPrice(BigDecimal.valueOf(10.99));
        book1.setDescription("Test Description");
        book1.setInventory(100);
        book1.setImage("Test Image");
        Book book2 = new Book();
        book2.setId(1);
        book2.setIsbn("1234");
        book2.setName("Test Book");
        book2.setType("Test Type");
        book2.setAuthor("Test Author");
        book2.setPrice(BigDecimal.valueOf(10.99));
        book2.setDescription("Test Description");
        book2.setInventory(100);
        book2.setImage("Test Image");
        assertEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForNullBook() {
        Book book1 = new Book();
        book1.setId(1);
        assertNotEquals(book1, null);
    }

    @Test
    void equalsReturnsFalseForNotBookObject() {
        Book book1 = new Book();
        book1.setId(1);
        assertNotEquals(book1, new Object());
    }

    @Test
    void equalsReturnsFalseForDifferentId() {
        Book book1 = new Book();
        book1.setId(1);
        Book book2 = new Book();
        book2.setId(2);
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentInventory() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setInventory(100);
        Book book2 = new Book();
        book2.setId(1);
        book2.setInventory(200);
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentIsbn() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setIsbn("1234");
        Book book2 = new Book();
        book2.setId(1);
        book2.setIsbn("5678");
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentName() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Test Book");
        Book book2 = new Book();
        book2.setId(1);
        book2.setName("New Test Book");
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setType("Test Type");
        Book book2 = new Book();
        book2.setId(1);
        book2.setType("New Test Type");
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentAuthor() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setAuthor("Test Author");
        Book book2 = new Book();
        book2.setId(1);
        book2.setAuthor("New Test Author");
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentPrice() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setPrice(BigDecimal.valueOf(10.99));
        Book book2 = new Book();
        book2.setId(1);
        book2.setPrice(BigDecimal.valueOf(20.99));
        assertNotEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentDescription() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setDescription("Test Description");
        Book book2 = new Book();
        book2.setId(1);
        book2.setDescription("New Test Description");
        assertNotEquals(book1, book2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameBook() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setIsbn("1234");
        book1.setName("Test Book");
        book1.setType("Test Type");
        book1.setAuthor("Test Author");
        book1.setPrice(BigDecimal.valueOf(10.99));
        book1.setDescription("Test Description");
        book1.setInventory(100);
        book1.setImage("Test Image");
        Book book2 = new Book();
        book2.setId(1);
        book2.setIsbn("1234");
        book2.setName("Test Book");
        book2.setType("Test Type");
        book2.setAuthor("Test Author");
        book2.setPrice(BigDecimal.valueOf(10.99));
        book2.setDescription("Test Description");
        book2.setInventory(100);
        book2.setImage("Test Image");
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void hashCodeReturnsDifferentHashCodeForDifferentBooks() {
        Book book1 = new Book();
        book1.setId(1);
        Book book2 = new Book();
        book2.setId(2);
        assertNotEquals(book1.hashCode(), book2.hashCode());
    }
}