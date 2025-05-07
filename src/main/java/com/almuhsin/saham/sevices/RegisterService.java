package com.almuhsin.saham.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.almuhsin.saham.dto.RegisterUserRequest;
import com.almuhsin.saham.entities.Biodata;
import com.almuhsin.saham.entities.User;
import com.almuhsin.saham.repositories.BiodataRepository;
import com.almuhsin.saham.repositories.UserRepository;
import com.almuhsin.saham.util.GenerateOTP;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class RegisterService {

    @Autowired
    private BiodataRepository biodataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public String register(RegisterUserRequest req) {
        // Validasi input
        if (req == null) {
            return "Request tidak boleh kosong";
        }
        if (req.getCreatedBy() == null) {
            return "ID admin tidak boleh kosong";
        }
        if (req.getPassword() == null || req.getPassword().isEmpty()) {
            return "Password tidak boleh kosong";
        }
        if (req.getRole() == null || req.getRole().isEmpty()) {
            return "Role tidak boleh kosong";
        }
        if (req.getName() == null || req.getName().isEmpty()) {
            return "Nama tidak boleh kosong"; 
        }
        if (req.getEmail() == null || req.getEmail().isEmpty()) {
            return "Email tidak boleh kosong";
        }
        if (req.getPhoneNumber() == null || req.getPhoneNumber().isEmpty()) {
            return "Nomor telepon tidak boleh kosong";
        }
        if (biodataRepository.existsByEmail(req.getEmail())) {  
            return "Email sudah terdaftar";
        }
        //generate token
        String token = GenerateOTP.generateOTP(6);
        // kirim token ke email
        emailService.sendOtpEmail(req.getEmail(), token);


                // 1. Buat Biodata
        Biodata biodata = new Biodata();
        biodata.setName(req.getName());
        biodata.setEmail(req.getEmail());
        biodata.setPhoneNumber(req.getPhoneNumber());
        biodata.setAddress(req.getAddress());
        biodata.setParentName(req.getParentName());
        biodata.setIsActive(true);
        biodata.setIsDeleted(false);
        biodata.setCreatedAt(LocalDateTime.now());
        biodata.setModifiedAt(LocalDateTime.now());
        biodata.setCreatedBy(req.getCreatedBy());
        biodata.setModifyBy(req.getCreatedBy());
        Biodata savedBiodata = biodataRepository.save(biodata);

        // 2. Buat User
        User user = new User();
        user.setUsername(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        user.setBiodata(savedBiodata);
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        user.setCreatedBy(req.getCreatedBy());
        user.setModifiedBy(req.getCreatedBy());
        user.setIsLocked(false);
        user.setLoginAttemp(0);
        //user.setIsDeleted(false);

        userRepository.save(user);

        return "User berhasil didaftarkan";
    }
}
