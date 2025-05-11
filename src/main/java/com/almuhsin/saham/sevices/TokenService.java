package com.almuhsin.saham.sevices;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almuhsin.saham.entities.MToken;
import com.almuhsin.saham.entities.User;
import com.almuhsin.saham.repositories.TokenRepository;
import com.almuhsin.saham.repositories.UserRepository;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;
    
    public void saveToken(String token, String usedFor, int userId) {
        // Implementasi untuk menyimpan token ke database
        // cek apakah token sudah ada di database
        // jika sudah ada, ubah status token menjadi expired
        // jika belum ada, simpan token baru

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Cek apakah token sudah ada di database
        Optional<MToken> existingToken = tokenRepository.findValidOtpRegister(userId, usedFor);
        if (existingToken.isPresent()) {
            MToken tokenEntity = existingToken.get();
            tokenEntity.setExpiredAt(LocalDateTime.now().plusMinutes(5)); // Set expired time 5 menit dari sekarang
            tokenEntity.setIsUsed(false); // Set isUsed menjadi false
            tokenEntity.setToken(token); // Update token
            tokenRepository.save(tokenEntity);
        }else {
            // Jika token belum ada, buat token baru
            MToken newToken = new MToken();
            newToken.setToken(token);
            newToken.setTokenFor(usedFor);
            newToken.setExpiredAt(LocalDateTime.now().plusMinutes(5)); // Set expired time 5 menit dari sekarang
            newToken.setUser(user); // Set user ke null karena belum ada user yang terdaftar
            tokenRepository.save(newToken);
        }


    }
    public void verifToken(String token, int userId, String tokenFor) {
        // Implementasi untuk memverifikasi token
        // Misalnya, Anda bisa memanggil metode dari TokenRepository untuk memeriksa token
        // dan mengembalikan respons yang sesuai
        Optional<MToken> existingToken = tokenRepository.findValidOtp(userId, tokenFor, token);
        if (existingToken.isPresent()) {
            MToken tokenEntity = existingToken.get();
            tokenEntity.setIsUsed(true); // Set isUsed menjadi true
            tokenRepository.save(tokenEntity);
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }
}
