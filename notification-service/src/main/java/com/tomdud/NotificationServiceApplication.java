package com.tomdud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(TransactionEvent transactionEvent) {
        log.info("Received Notification for transaction - from {} to {} , quantity {}$.",
                transactionEvent.getAccountNumberFrom(),
                transactionEvent.getAccountNumberTo(),
                transactionEvent.getQuantity());
    }

}