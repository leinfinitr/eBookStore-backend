package com.example.ebookstore.repository;

import com.example.ebookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int id);

    User findByName(String name);
}
