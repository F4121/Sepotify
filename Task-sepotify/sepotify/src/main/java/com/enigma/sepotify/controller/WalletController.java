package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Profile;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.entity.WalletHistory;
import com.enigma.sepotify.services.WalletHistoryService;
import com.enigma.sepotify.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable String id){
        return walletService.getWallet(id);
    }

    @PostMapping
    public Wallet saveWallet(@RequestBody Wallet wallet){
        return walletService.saveWallet(wallet);
    }

    @PostMapping(value = "/topup")
    public void topUpWallet(@RequestBody Wallet wallet){
        Double lastBalance = walletService.getWallet(wallet.getId()).getBalance();
        wallet.setBalance(lastBalance + wallet.getTopUp());
        walletService.topUpWallet(wallet);
    }

    @PostMapping(value = "/withdrawl")
    public void withDrawlWallet(@RequestBody Wallet wallet){
        Double lastBalance = walletService.getWallet(wallet.getId()).getBalance();
        wallet.setBalance(lastBalance - wallet.getWithdrawl());
        walletService.withdrawlWallet(wallet);
    }

    @DeleteMapping
    public void deleteWallet(@RequestBody Wallet wallet){
        walletService.deleteWallet(wallet.getId());
    }
}
