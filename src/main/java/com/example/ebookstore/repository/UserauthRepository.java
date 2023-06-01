package com.example.ebookstore.repository;

import com.example.ebookstore.entity.Userauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserauthRepository extends JpaRepository<Userauth, Integer> {
    Userauth findByUserId(int id);
}
