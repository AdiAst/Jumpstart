package com.aceadoratech.jumpstart.controller;
import com.aceadoratech.jumpstart.exchanges.AuthenticationRequest;
import com.aceadoratech.jumpstart.exchanges.AuthenticationResponse;
import com.aceadoratech.jumpstart.exchanges.RegisterRequest;
import com.aceadoratech.jumpstart.service.AuthenticationService;
import com.aceadoratech.jumpstart.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/base/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class AuthenticationController {

    // services ==================================================
    private final AuthenticationService service;

    // users ==================================================
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        service.register(registerRequest);
        return ResponseEntity.ok("Successfully Register");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(service.authenticate(authRequest));
    }
}