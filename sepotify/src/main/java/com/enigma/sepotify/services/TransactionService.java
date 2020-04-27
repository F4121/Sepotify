package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    public Transaction saveTransaction(Transaction transaction);
    public Transaction getTransaction(String id);
    public Page<Transaction> searchTransaction(Pageable pageable, Transaction searchForm);
    public void deleteTransaction(String id);
}
