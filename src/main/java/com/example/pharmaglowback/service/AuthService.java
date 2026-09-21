package com.example.pharmaglowback.service;

import com.example.pharmaglowback.dto.request.ChangePasswordRequest;
import com.example.pharmaglowback.dto.request.LoginRequest;
import com.example.pharmaglowback.dto.response.LoginResponse;
import com.example.pharmaglowback.model.Admin;
import com.example.pharmaglowback.repository.AdminRepository;
import com.example.pharmaglowback.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token, userDetails.getUsername());
    }

    @Transactional
    public void changePassword(String username, ChangePasswordRequest request) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Admin not found"));

        if (!passwordEncoder.matches(request.currentPassword(), admin.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }

        admin.setPassword(passwordEncoder.encode(request.newPassword()));
        adminRepository.save(admin);
    }
}
