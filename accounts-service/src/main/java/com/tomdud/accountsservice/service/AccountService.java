package com.tomdud.accountsservice.service;

import com.tomdud.accountsservice.dto.AccountResponse;
import com.tomdud.accountsservice.model.Account;
import com.tomdud.accountsservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    @Transactional
    public boolean haveEnoughMoney(Long accountId, Long quantity) throws UserPrincipalNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if(optionalAccount.isEmpty()) throw new UserPrincipalNotFoundException("");

        if(optionalAccount.get().getBalance() >= quantity)
            return true;
        else
            return false;
    }
    @Transactional
    public AccountResponse getByAccountNumber(String accountNumber) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if(optionalAccount.isEmpty()) throw new RuntimeException("No account found");
        Account account = optionalAccount.get();

        AccountResponse accountResponse  =
                AccountResponse.builder()
                        .accountId(account.getId())
                        .accountNumber(account.getAccountNumber())
                        .build();

        return accountResponse;
    }

    @Transactional
    public boolean makeBalance(Long accountIdFrom, Long accountIdTo, Long amount) {
        Optional<Account> optionalAccountFrom = accountRepository.findById(accountIdFrom);
        Optional<Account> optionalAccountTo = accountRepository.findById(accountIdTo);

        if(optionalAccountTo.isEmpty() || optionalAccountFrom.isEmpty())
            throw new RuntimeException("Account not found");

        Account from = optionalAccountFrom.get();
        Account to = optionalAccountTo.get();

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        return true;
    }
}
