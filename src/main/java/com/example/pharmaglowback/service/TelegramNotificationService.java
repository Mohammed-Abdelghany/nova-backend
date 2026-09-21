package com.example.pharmaglowback.service;

import com.example.pharmaglowback.config.TelegramProperties;
import com.example.pharmaglowback.dto.request.ContactRequest;
import com.example.pharmaglowback.dto.response.OrderItemResponse;
import com.example.pharmaglowback.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramNotificationService {

    private final RestClient telegramRestClient;
    private final TelegramProperties telegramProperties;

    @Async
    public void notifyNewOrder(OrderResponse order) {
        String items = order.items().stream()
                .map(TelegramNotificationService::formatItem)
                .reduce("", (a, b) -> a + b);

        String text = """
                🛒 New Order #%d
                Customer: %s
                Phone: %s
                Governorate: %s
                Address: %s
                Items:
                %sDelivery fee: %s EGP
                Total: %s EGP
                """.formatted(
                order.id(),
                order.customerName(),
                order.customerPhone(),
                order.governorate(),
                order.address(),
                items,
                order.deliveryFee(),
                order.totalPrice()
        );

        try {
            telegramRestClient.post()
                    .uri("/bot{token}/sendMessage?chat_id={chatId}&text={text}",
                            telegramProperties.botToken(), telegramProperties.chatId(), text)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Failed to send Telegram notification for order {}", order.id(), e);
        }
    }

    private static String formatItem(OrderItemResponse item) {
        return "- %s x%d = %s EGP%n".formatted(item.productTitle(), item.quantity(), item.lineTotal());
    }

    @Async
    public void notifyContactMessage(ContactRequest request) {
        String text = """
                📩 New Contact Message
                Name: %s
                Phone: %s
                Message: %s
                """.formatted(request.name(), request.phone(), request.message());

        try {
            telegramRestClient.post()
                    .uri("/bot{token}/sendMessage?chat_id={chatId}&text={text}",
                            telegramProperties.botToken(), telegramProperties.chatId(), text)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Failed to send Telegram notification for contact message from {}", request.name(), e);
        }
    }
}
