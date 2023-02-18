package com.tomdud.transactionsservice.service;

import com.tomdud.transactionsservice.dto.AccountResponse;
import com.tomdud.transactionsservice.event.TransactionEvent;
import com.tomdud.transactionsservice.model.Transaction;
import com.tomdud.transactionsservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Calendar;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;
    @Autowired
    public TransactionService(
            TransactionRepository transactionRepository,
            WebClient.Builder webClientBuilder,
            KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.transactionRepository = transactionRepository;
        this.webClientBuilder =  webClientBuilder;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Transaction makeCommonTransaction(String from, String to, Long amount) {
        //Check existence of two of account
        AccountResponse accountFrom =
                webClientBuilder.build().get()
                    .uri("http://accounts-service/accounts/" + from)
                    .retrieve()
                    .bodyToMono(AccountResponse.class)
                    .block();

        AccountResponse accountTo =
                webClientBuilder.build().get()
                        .uri("http://accounts-service/accounts/" + to)
                        .retrieve()
                        .bodyToMono(AccountResponse.class)
                        .block();

        String makeBalanceURI = String.format(
                "http://accounts-service/accounts/balance/from/%d/to/%d/amount/%d",
                accountFrom.getAccountId(),
                accountTo.getAccountId(),
                amount);

        Boolean check = webClientBuilder.build().put()
                .uri(makeBalanceURI)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(!check) throw new RuntimeException("");

        Transaction newTransaction =
                Transaction.builder()
                        .id(0L)
                        .accountIdFrom(accountFrom.getAccountId())
                        .accountIdTo(accountTo.getAccountId())
                        .quantity(amount)
                        .date(Calendar.getInstance().getTime())
                        .build();

        kafkaTemplate.send("notificationTopic", new TransactionEvent(
                accountFrom.getAccountNumber(),
                accountTo.getAccountNumber(),
                newTransaction.getQuantity()
        ));

        return transactionRepository.save(newTransaction);
    }





}
