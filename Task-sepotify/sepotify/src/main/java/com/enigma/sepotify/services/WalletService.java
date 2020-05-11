package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.entity.WalletHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    public Wallet saveWallet(Wallet wallet);
    public Wallet getWallet(String id);
    public Page<Wallet> searchWallet(Pageable pageable, Wallet searchForm);
    public void deleteWallet(String id);
    public void topUpWallet(Wallet wallet);
    public void withdrawlWallet(Wallet wallet);
    public Boolean isWalletAlready(Wallet wallet);
    public void transactionlWallet(Wallet wallet, Double amount);
    public Account isOwnerActive(Wallet wallet);
}
