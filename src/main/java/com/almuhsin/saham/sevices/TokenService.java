package com.almuhsin.saham.sevices;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almuhsin.saham.entities.MToken;
import com.almuhsin.saham.repositories.TokenRepository;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    
    public void saveToken(int token, String usedFor, int userId) {
        // Implementasi untuk menyimpan token ke database
        MToken tokenEntity = new MToken();
        tokenEntity.setToken(token);
        tokenEntity.setTokenFor(usedFor);
        tokenEntity.setExpiredAt(LocalDateTime.now().plusMinutes(5)); // Set expired time 5 menit dari sekarang
        tokenEntity.setCreatedAt(LocalDateTime.now());
        tokenEntity.setCreatedBy(userId);
        tokenEntity.setModifiedAt(LocalDateTime.now());
        tokenEntity.setModifiedBy(userId);
        tokenEntity.setIsExpired(false);
        tokenEntity.setUser(null); // Set user ke null karena belum ada user yang terdaftar
        tokenRepository.save(tokenEntity);

    }
}
