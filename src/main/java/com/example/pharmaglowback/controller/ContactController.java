package com.example.pharmaglowback.controller;

import com.example.pharmaglowback.dto.request.ContactRequest;
import com.example.pharmaglowback.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Void> submitContactMessage(@Valid @RequestBody ContactRequest request) {
        contactService.submit(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
