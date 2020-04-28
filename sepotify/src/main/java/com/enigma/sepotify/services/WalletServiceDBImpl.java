package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.entity.WalletHistory;
import com.enigma.sepotify.enums.HistoryTypeEnum;
import com.enigma.sepotify.exception.AccountIsAlreadyHaveWalletException;
import com.enigma.sepotify.exception.AccountIsNotActiveException;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class WalletServiceDBImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    WalletHistoryService walletHistoryService;

    @Override
    public Wallet saveWallet(Wallet wallet) {
        this.isOwnerActive(wallet);
        this.isWalletAlready(wallet);
        return walletRepository.save(wallet);
    }

    @Override
    public void topUpWallet(Wallet wallet) {
        Account owner = this.isOwnerActive(wallet);

        wallet.setOwner(owner);
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setAmount(wallet.getTopUp());
        walletHistory.setTrxDate(new Timestamp(new Date().getTime()));
        walletHistory.setType(HistoryTypeEnum.TOPUP);
        walletHistory.setWallet(wallet);

        walletRepository.save(wallet);
        walletHistoryService.saveWalletHistory(walletHistory);
    }

    @Override
    public void withdrawlWallet(Wallet wallet) {
        Account owner = this.isOwnerActive(wallet);

        wallet.setOwner(owner);
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setAmount(wallet.getWithdrawl());
        walletHistory.setTrxDate(new Timestamp(new Date().getTime()));
        walletHistory.setType(HistoryTypeEnum.WITHDRAWAL);
        walletHistory.setWallet(wallet);

        walletRepository.save(wallet);
        walletHistoryService.saveWalletHistory(walletHistory);
    }

    @Override
    public void transactionlWallet(Wallet wallet, Double amount) {
        Account owner = this.isOwnerActive(wallet);

        //Set Wallet
        wallet.setBalance(wallet.getBalance() - amount);
        wallet.setOwner(owner);

        //Wallet History
        WalletHistory walletHistory = new WalletHistory();
        //Set Wallet History
        walletHistory.setAmount(amount);
        walletHistory.setTrxDate(new Timestamp(new Date().getTime()));
        walletHistory.setType(HistoryTypeEnum.PAYMENT);
        walletHistory.setWallet(wallet);

        walletRepository.save(wallet);
        walletHistoryService.saveWalletHistory(walletHistory);
    }

    @Override
    public Wallet getWallet(String id) {
        Wallet wallet;
        if (walletRepository.findById(id).isPresent()) wallet = walletRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Wallet.class);
        return wallet;
    }

    @Override
    public Page<Wallet> searchWallet(Pageable pageable, Wallet searchForm) {
        return null;
    }

    @Override
    public void deleteWallet(String id) {
        if (walletRepository.findById(id).isPresent()) walletRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Wallet.class);
    }

    public Account isOwnerActive(Wallet wallet){
        Account account = accountService.getAccount(this.getWallet(wallet.getId()).getOwner().getId());
        if(!account.getActive()){
            throw new AccountIsNotActiveException(wallet.getOwner().getId());
        }
        return account;
    }

    public Boolean isWalletAlready(Wallet wallet){
        Account account = accountService.getAccount(this.getWallet(wallet.getId()).getOwner().getId());
        if(account.getWallet() != null){
            throw new AccountIsAlreadyHaveWalletException(account.getId());
        }
        return true;
    }
}
