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
    public Transaction saveTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }

//    @PostMapping(value = "/album")
//    public void saveUpWallet(@RequestBody Wallet wallet){
//        transactionService.saveTransaction(transaction);
//    }

//    @PostMapping(value = "/withdrawl")
//    public void withDrawlWallet(@RequestBody Wallet wallet){
//        Double lastBalance = walletService.getWallet(wallet.getId()).getBalance();
//        wallet.setBalance(lastBalance - wallet.getWithdrawl());
//        walletService.withdrawlWallet(wallet);
//    }
}
