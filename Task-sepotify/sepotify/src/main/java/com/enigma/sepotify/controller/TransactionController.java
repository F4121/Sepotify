package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Transaction;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.services.TransactionService;
import com.enigma.sepotify.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id){
        return transactionService.getTransaction(id);
    }

    @PostMapping
    public void saveTransaction(@RequestBody Transaction transaction){
        transactionService.saveTransaction(transaction);
    }

    @PostMapping("/album")
    public void saveAlbumTransaction(@RequestBody Transaction transaction){
        transactionService.saveAlbumTransaction(transaction);
    }
}
