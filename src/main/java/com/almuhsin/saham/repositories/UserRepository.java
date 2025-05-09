package com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almuhsin.saham.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // method to find email sudah terdaftar pake query
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
       "FROM User u " +
       "JOIN u.biodata b " +
       "WHERE b.email = :email AND u.isEmailVerified = true")
    boolean isEmailRegistered(@Param("email") String email);


}