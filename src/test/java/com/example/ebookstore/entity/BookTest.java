package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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
        Book book2 = new Book();
        book2.setId(1);
        assertEquals(book1, book2);
    }

    @Test
    void equalsReturnsFalseForDifferentBooks() {
        Book book1 = new Book();
        book1.setId(1);
        Book book2 = new Book();
        book2.setId(2);
        assertNotEquals(book1, book2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameBook() {
        Book book1 = new Book();
        book1.setId(1);
        Book book2 = new Book();
        book2.setId(1);
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