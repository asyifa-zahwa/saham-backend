package com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almuhsin.saham.entities.MToken;

@Repository
public interface TokenRepository extends JpaRepository<MToken, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by token value or user ID
   

    
}