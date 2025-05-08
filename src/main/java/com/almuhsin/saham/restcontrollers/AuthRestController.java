package com.almuhsin.saham.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.almuhsin.saham.dto.RegisterUserRequest;
import com.almuhsin.saham.sevices.RegisterService;
import com.almuhsin.saham.sevices.VerifEmail;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private VerifEmail verifEmailService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        //System.out.println("Register hit: " + request.get());
        return ResponseEntity.ok(registerService.register(request));
    }
    @GetMapping("/verify-email/{email}/{userId}")
    public ResponseEntity<?> verifyEmail(@PathVariable String email, @PathVariable int userId) {
        String result = verifEmailService.verifEmail(email, userId);
        if (result != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok("Token sent to email: " + email);
    }
}
