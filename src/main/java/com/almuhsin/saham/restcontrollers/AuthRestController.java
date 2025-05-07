package com.almuhsin.saham.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.almuhsin.saham.dto.RegisterUserRequest;
import com.almuhsin.saham.sevices.RegisterService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        //System.out.println("Register hit: " + request.get());
        return ResponseEntity.ok(registerService.register(request));
    }
}
