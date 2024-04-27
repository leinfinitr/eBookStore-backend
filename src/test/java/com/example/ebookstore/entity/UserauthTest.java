package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserauthTest {

    @Test
    void setIdAndGetIdWorkCorrectly() {
        Userauth userauth = new Userauth();
        userauth.setId(1);
        assertEquals(1, userauth.getId());
    }

    @Test
    void setUserIdAndGetUserIdWorkCorrectly() {
        Userauth userauth = new Userauth();
        userauth.setUserId(2);
        assertEquals(2, userauth.getUserId());
    }

    @Test
    void setPasswordAndGetPasswordWorkCorrectly() {
        Userauth userauth = new Userauth();
        userauth.setPassword("Test Password");
        assertEquals("Test Password", userauth.getPassword());
    }

    @Test
    void equalsReturnsTrueForSameUserauth() {
        Userauth userauth1 = new Userauth();
        userauth1.setId(1);
        userauth1.setUserId(2);
        userauth1.setPassword("Test Password");

        Userauth userauth2 = new Userauth();
        userauth2.setId(1);
        userauth2.setUserId(2);
        userauth2.setPassword("Test Password");

        assertEquals(userauth1, userauth2);
    }

    @Test
    void equalsReturnsFalseForDifferentUserauths() {
        Userauth userauth1 = new Userauth();
        userauth1.setId(1);
        userauth1.setUserId(2);
        userauth1.setPassword("Test Password");

        Userauth userauth2 = new Userauth();
        userauth2.setId(3);
        userauth2.setUserId(4);
        userauth2.setPassword("Different Test Password");

        assertNotEquals(userauth1, userauth2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameUserauth() {
        Userauth userauth1 = new Userauth();
        userauth1.setId(1);
        userauth1.setUserId(2);
        userauth1.setPassword("Test Password");

        Userauth userauth2 = new Userauth();
        userauth2.setId(1);
        userauth2.setUserId(2);
        userauth2.setPassword("Test Password");

        assertEquals(userauth1.hashCode(), userauth2.hashCode());
    }

    @Test
    void hashCodeReturnsDifferentHashCodeForDifferentUserauths() {
        Userauth userauth1 = new Userauth();
        userauth1.setId(1);
        userauth1.setUserId(2);
        userauth1.setPassword("Test Password");

        Userauth userauth2 = new Userauth();
        userauth2.setId(3);
        userauth2.setUserId(4);
        userauth2.setPassword("Different Test Password");

        assertNotEquals(userauth1.hashCode(), userauth2.hashCode());
    }
}