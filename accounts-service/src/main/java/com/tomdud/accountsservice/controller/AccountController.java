package com.tomdud.accountsservice.controller;

import com.tomdud.accountsservice.dto.AccountResponse;
import com.tomdud.accountsservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/from/{accountId}/amount/{amount}")
    public boolean canMakeTransaction(@PathVariable Long accountId, @PathVariable Long amount) throws UserPrincipalNotFoundException {
        return accountService.haveEnoughMoney(accountId, amount);
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.getByAccountNumber(accountNumber);
    }

    @PutMapping("/balance/from/{accountIdFrom}/to/{accountIdTo}/amount/{amount}")
    public boolean makeBalance(@PathVariable Long accountIdFrom, @PathVariable Long accountIdTo, @PathVariable Long amount) {
        return accountService.makeBalance(accountIdFrom, accountIdTo, amount);
    }




}
