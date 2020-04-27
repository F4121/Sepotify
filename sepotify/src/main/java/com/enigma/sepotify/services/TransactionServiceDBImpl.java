package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Song;
import com.enigma.sepotify.entity.Transaction;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TransactionServiceDBImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletService walletService;

    @Autowired
    SongService songService;


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        //Wallet
        Wallet wallet = walletService.getWallet(transaction.getWallet().getId());
        Song item = songService.getSong(transaction.getItem().getId());

        //Set Transaction
        transaction.setAmount(item.getPrice());
        transaction.setTrxDate(new Timestamp(new Date().getTime()));

        //Update Wallet
        walletService.transactionlWallet(wallet, transaction.getAmount());
        //Save Transaction
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransaction(String id) {
        Transaction transaction;
        if (transactionRepository.findById(id).isPresent()) transaction = transactionRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Transaction.class);
        return transaction;
    }

    @Override
    public Page<Transaction> searchTransaction(Pageable pageable, Transaction searchForm) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {
        if (transactionRepository.findById(id).isPresent()) transactionRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Transaction.class);
    }
}
