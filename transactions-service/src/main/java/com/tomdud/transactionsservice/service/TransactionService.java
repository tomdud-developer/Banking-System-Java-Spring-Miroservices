package com.tomdud.transactionsservice.service;

import com.tomdud.transactionsservice.dto.AccountResponse;
import com.tomdud.transactionsservice.model.Transaction;
import com.tomdud.transactionsservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Calendar;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebClient webClient;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, WebClient  webClient) {
        this.transactionRepository = transactionRepository;
        this. webClient =  webClient;
    }

    @Transactional
    public Transaction makeCommonTransaction(String from, String to, Long amount) {
        //Check existence of two of account
        AccountResponse accountFrom =
                webClient.get()
                    .uri("http://localhost:8081/accounts/" + from)
                    .retrieve()
                    .bodyToMono(AccountResponse.class)
                    .block();

        AccountResponse accountTo =
                webClient.get()
                        .uri("http://localhost:8081/accounts/" + to)
                        .retrieve()
                        .bodyToMono(AccountResponse.class)
                        .block();

        String makeBalanceURI = String.format(
                "http://localhost:8081/accounts/balance/from/%d/to/%d/amount/%d",
                accountFrom.getAccountId(),
                accountTo.getAccountId(),
                amount);

        Boolean check = webClient.put()
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
        return transactionRepository.save(newTransaction);
    }





}
