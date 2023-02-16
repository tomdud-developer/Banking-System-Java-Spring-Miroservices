package com.tomdud.accountsservice.service;

import com.tomdud.accountsservice.model.Account;
import com.tomdud.accountsservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    public boolean haveEnoughMoney(Long accountId, Long quantity) throws UserPrincipalNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if(optionalAccount.isEmpty()) throw new UserPrincipalNotFoundException("");

        if(optionalAccount.get().getBalance() >= quantity)
            return true;
        else
            return false;
    }
}
