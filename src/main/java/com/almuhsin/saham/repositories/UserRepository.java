package com.almuhsin.saham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almuhsin.saham.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {}