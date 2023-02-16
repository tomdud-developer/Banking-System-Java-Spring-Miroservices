package com.tomdud.transactionsservice.service;

import com.tomdud.transactionsservice.Transaction;
import com.tomdud.transactionsservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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
