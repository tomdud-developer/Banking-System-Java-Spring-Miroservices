package com.tomdud.transactionsservice;

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

    @PostMapping("/from/{from}/to/{to}/amount/{amount}")
    public Transaction makeCommonTransaction(@PathVariable Long from, @PathVariable Long to, @PathVariable Long amount) {
        return  transactionService.makeCommonTransaction(from, to, amount);
    }


}
