package com.tomdud.transactionsservice.controller;

import com.tomdud.transactionsservice.model.Transaction;
import com.tomdud.transactionsservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionService transactionService;

    @PostMapping("/from/{from}/to/{to}/amount/{quantity}")
    public Transaction makeCommonTransaction(@PathVariable String from, @PathVariable String to, @PathVariable Long quantity) {
        return  transactionService.makeCommonTransaction(from, to, quantity);
    }


}
