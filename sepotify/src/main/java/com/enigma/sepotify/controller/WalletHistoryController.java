package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.WalletHistory;
import com.enigma.sepotify.services.AccountService;
import com.enigma.sepotify.services.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet-history")
public class WalletHistoryController {

    @Autowired
    WalletHistoryService walletHistoryService;

    @GetMapping("/{id}")
    public WalletHistory getAccountById(@PathVariable String id){
        return walletHistoryService.getWalletHistory(id);
    }

    @PostMapping
    public void saveAccount(@RequestBody WalletHistory walletHistory){
        walletHistoryService.saveWalletHistory(walletHistory);
    }

    @DeleteMapping
    public void deleteAccount(@RequestBody WalletHistory walletHistory){
        walletHistoryService.deleteWalletHistory(walletHistory.getId());
    }
}
