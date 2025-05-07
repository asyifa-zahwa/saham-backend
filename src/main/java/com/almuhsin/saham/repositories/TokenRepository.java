package com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almuhsin.saham.entities.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by token value or user ID
    Token findByToken(String token);
    Token findByUserId(Long userId);

    
}