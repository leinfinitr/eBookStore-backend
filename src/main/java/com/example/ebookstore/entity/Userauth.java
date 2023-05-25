package com.example.ebookstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Userauth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

        if (!Objects.equals(userName, userauth.userName)) return false;
        return Objects.equals(password, userauth.password);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
