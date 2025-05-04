package com.almuhsin.saham.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.almuhsin.saham.dto.RegisterUserRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    //@Autowired
    //private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok("registerService.register(request)");
    }
}
