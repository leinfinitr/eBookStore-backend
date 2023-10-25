package com.example.ebookstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Userauth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userauth userauth = (Userauth) o;

        if (id != userauth.id) return false;
        if (userId != userauth.userId) return false;
        return Objects.equals(password, userauth.password);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
