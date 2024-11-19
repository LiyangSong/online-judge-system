package com.liyang.onlinejudgesystem.controller;

import com.liyang.onlinejudgesystem.dto.LoginDto;
import com.liyang.onlinejudgesystem.dto.RegisterDto;
import com.liyang.onlinejudgesystem.security.JwtUserDetailsService;
import com.liyang.onlinejudgesystem.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtUtils jwtUtils;

    // Create a new user, hash password and save to database
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String encryptedPassword = passwordEncoder.encode(registerDto.userPassword());
        jwtUserDetailsService.register(registerDto, encryptedPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // Authenticate user, generate and return JWT to user if success
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        try {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginDto.username());
            if (!passwordEncoder.matches(loginDto.userPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            String token = jwtUtils.generateToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
