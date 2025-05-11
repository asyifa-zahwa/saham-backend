package com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.almuhsin.saham.entities.MToken;

@Repository
public interface TokenRepository extends JpaRepository<MToken, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by token value or user ID
//     @Query(value = "SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
//             "FROM MToken t " +
//             "WHERE t.token = :token AND t.isExpired = false AND t.tokenFor = :usedFor", nativeQuery = true)
//    void isTokenExists(@Param("email") String email, @Param("usedFor") String usedFor);

    
}