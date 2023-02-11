package com.tomdud.transactionsservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionService transactionService;

    @PostMapping("/from/{from}/to/{to}/amount/{amount}")
    public Transaction makeCommonTransaction(@PathVariable Long from, @PathVariable Long to, @PathVariable Long amount) {
        return  transactionService.makeCommonTransaction(from, to, amount);
    }


}
