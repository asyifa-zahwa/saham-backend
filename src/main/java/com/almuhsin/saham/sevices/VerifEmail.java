package com.almuhsin.saham.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almuhsin.saham.repositories.BiodataRepository;
import com.almuhsin.saham.repositories.UserRepository;
import com.almuhsin.saham.util.GenerateOTP;

@Service
public class VerifEmail {
    
    @Autowired
    private BiodataRepository biodataRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public String verifEmail(String email, int userID) {
        // Validasi format email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            return "Format email tidak valid";
        }
        // Cek apakah email sudah terdaftar di database
        if (userRepository.isEmailRegistered(email)) {    
            return "Email sudah terdaftar";
        }
        //generate token
        String token = GenerateOTP.generateOTP(6);
        // kirim token ke email
        emailService.sendOtpEmail(email, token);
        //ganti token jadi int
        int tokenInt = Integer.parseInt(token);
        //simpan token ke database
        tokenService.saveToken(tokenInt, "verifEmail", userID);

        return null;
    }
}
