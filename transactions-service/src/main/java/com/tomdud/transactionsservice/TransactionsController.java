package com.tomdud.transactionsservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("transactions")
public class TransactionsController {

    @PostMapping("/from/{from}/to/{to}/amount/{amount}")
    public void makeCommonTransaction(@PathVariable Long from, @PathVariable Long to, @PathVariable Long amount) {

    }
}
