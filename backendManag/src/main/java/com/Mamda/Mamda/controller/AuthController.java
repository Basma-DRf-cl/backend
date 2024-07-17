package com.Mamda.Mamda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Mamda.Mamda.model.Admin;
import com.Mamda.Mamda.payload.request.SignInRequest;
import com.Mamda.Mamda.payload.request.SignUpRequest;
import com.Mamda.Mamda.payload.response.JwtResponse;
import com.Mamda.Mamda.payload.response.MessageResponse;
import com.Mamda.Mamda.repository.AdminRepository;
import com.Mamda.Mamda.security.jwt.JwtUtils;
import com.Mamda.Mamda.security.services.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateAdmin(@RequestBody SignInRequest loginRequest) {
    
        // Authentication logic remains the same
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
    
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
    
        return ResponseEntity.ok(jwtResponse);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerAdmin(@RequestBody SignUpRequest signUpRequest) {
        if (adminRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
    
        Admin admin = new Admin();
        admin.setUsername(signUpRequest.getUsername());
        admin.setEmail(signUpRequest.getEmail());
        admin.setPassword(encoder.encode(signUpRequest.getPassword()));
    
        adminRepository.save(admin);
    
        return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
    }
}    
