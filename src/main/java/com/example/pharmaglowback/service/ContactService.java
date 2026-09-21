package com.example.pharmaglowback.service;

import com.example.pharmaglowback.dto.request.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final TelegramNotificationService telegramNotificationService;

    public void submit(ContactRequest request) {
        telegramNotificationService.notifyContactMessage(request);
    }
}
