package com.tomdud.transactionsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction makeCommonTransaction(Long from, Long to, Long amount) {
        Transaction newTransaction = new Transaction(0L, from, to, amount, Calendar.getInstance().getTime());
        return transactionRepository.save(newTransaction);
    }



}
