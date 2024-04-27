package com.example.ebookstore.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {

    @Test
    void setIdAndGetIdWorkCorrectly() {
        User user = new User();
        user.setUserId(1);
        assertEquals(1, user.getUserId());
    }

    @Test
    void setNicknameAndGetNicknameWorkCorrectly() {
        User user = new User();
        user.setNickname("Test Nickname");
        assertEquals("Test Nickname", user.getNickname());
    }

    @Test
    void setNameAndGetNameWorkCorrectly() {
        User user = new User();
        user.setName("Test Name");
        assertEquals("Test Name", user.getName());
    }

    @Test
    void setPhoneAndGetPhoneWorkCorrectly() {
        User user = new User();
        user.setPhone("1234567890");
        assertEquals("1234567890", user.getPhone());
    }

    @Test
    void setEmailAndGetEmailWorkCorrectly() {
        User user = new User();
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void setNationAndGetNationWorkCorrectly() {
        User user = new User();
        user.setNation("Test Nation");
        assertEquals("Test Nation", user.getNation());
    }

    @Test
    void setProvinceAndGetProvinceWorkCorrectly() {
        User user = new User();
        user.setProvince("Test Province");
        assertEquals("Test Province", user.getProvince());
    }

    @Test
    void setAddressAndGetAddressWorkCorrectly() {
        User user = new User();
        user.setAddress("Test Address");
        assertEquals("Test Address", user.getAddress());
    }

    @Test
    void setTypeAndGetTypeWorkCorrectly() {
        User user = new User();
        user.setType("Test Type");
        assertEquals("Test Type", user.getType());
    }

    @Test
    void setStatusAndGetStatusWorkCorrectly() {
        User user = new User();
        user.setStatus("Test Status");
        assertEquals("Test Status", user.getStatus());
    }

    @Test
    void equalsReturnsTrueForSameUser() {
        User user1 = new User();
        user1.setUserId(1);
        user1.setNickname("Test Nickname");
        user1.setName("Test Name");
        user1.setPhone("1234567890");
        user1.setEmail("test@example.com");
        user1.setNation("Test Nation");
        user1.setProvince("Test Province");
        user1.setAddress("Test Address");
        user1.setType("Test Type");
        user1.setStatus("Test Status");

        User user2 = new User();
        user2.setUserId(1);
        user2.setNickname("Test Nickname");
        user2.setName("Test Name");
        user2.setPhone("1234567890");
        user2.setEmail("test@example.com");
        user2.setNation("Test Nation");
        user2.setProvince("Test Province");
        user2.setAddress("Test Address");
        user2.setType("Test Type");
        user2.setStatus("Test Status");

        assertEquals(user1, user2);
    }

    @Test
    void equalsReturnsFalseForDifferentUsers() {
        User user1 = new User();
        user1.setUserId(1);
        user1.setNickname("Test Nickname");
        user1.setName("Test Name");
        user1.setPhone("1234567890");
        user1.setEmail("test@example.com");
        user1.setNation("Test Nation");
        user1.setProvince("Test Province");
        user1.setAddress("Test Address");
        user1.setType("Test Type");
        user1.setStatus("Test Status");

        User user2 = new User();
        user2.setUserId(2);
        user2.setNickname("Different Test Nickname");
        user2.setName("Different Test Name");
        user2.setPhone("0987654321");
        user2.setEmail("different@example.com");
        user2.setNation("Different Test Nation");
        user2.setProvince("Different Test Province");
        user2.setAddress("Different Test Address");
        user2.setType("Different Test Type");
        user2.setStatus("Different Test Status");

        assertNotEquals(user1, user2);
    }

    @Test
    void hashCodeReturnsSameHashCodeForSameUser() {
        User user1 = new User();
        user1.setUserId(1);
        user1.setNickname("Test Nickname");
        user1.setName("Test Name");
        user1.setPhone("1234567890");
        user1.setEmail("test@example.com");
        user1.setNation("Test Nation");
        user1.setProvince("Test Province");
        user1.setAddress("Test Address");
        user1.setType("Test Type");
        user1.setStatus("Test Status");

        User user2 = new User();
        user2.setUserId(1);
        user2.setNickname("Test Nickname");
        user2.setName("Test Name");
        user2.setPhone("1234567890");
        user2.setEmail("test@example.com");
        user2.setNation("Test Nation");
        user2.setProvince("Test Province");
        user2.setAddress("Test Address");
        user2.setType("Test Type");
        user2.setStatus("Test Status");

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void hashCodeReturnsDifferentHashCodeForDifferentUsers() {
        User user1 = new User();
        user1.setUserId(1);
        user1.setNickname("Test Nickname");
        user1.setName("Test Name");
        user1.setPhone("1234567890");
        user1.setEmail("test@example.com");
        user1.setNation("Test Nation");
        user1.setProvince("Test Province");
        user1.setAddress("Test Address");
        user1.setType("Test Type");
        user1.setStatus("Test Status");

        User user2 = new User();
        user2.setUserId(2);
        user2.setNickname("Different Test Nickname");
        user2.setName("Different Test Name");
        user2.setPhone("0987654321");
        user2.setEmail("different@example.com");
        user2.setNation("Different Test Nation");
        user2.setProvince("Different Test Province");
        user2.setAddress("Different Test Address");
        user2.setType("Different Test Type");
        user2.setStatus("Different Test Status");

        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
}