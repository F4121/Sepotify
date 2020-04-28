package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.exception.AccountIsAlreadyHaveWalletException;
import com.enigma.sepotify.exception.AccountIsNotActiveException;
import com.enigma.sepotify.repository.AccountRepository;
import com.enigma.sepotify.repository.WalletRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
class WalletServiceDBImplTest {

    @Autowired
    WalletService walletService;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    Wallet wallet = new Wallet();

    @BeforeEach
    public void cleanUp(){
        walletRepository.deleteAll();
    }

    @Test
    void saveWallet_should_return_idNotNull() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);

        Wallet wallet = new Wallet();
        wallet.setBalance(15000.0);
        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);
        assertTrue(wallet.getId()!=null);
    }

    @Test
    void topUpWallet() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);

        Wallet wallet = new Wallet();
        wallet.setBalance(15000.0);
        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);
        wallet.setBalance(17000.0);
        walletService.topUpWallet(wallet);
        double expected = 17000.0;
        Wallet mywallet = walletRepository.findById(wallet.getId()).get();
        double actual = mywallet.getBalance();
        assertTrue(expected==actual);
    }

    @Test
    void withdrawlWallet() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);

        Wallet wallet = new Wallet();
        wallet.setBalance(15000.0);
        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);
        wallet.setBalance(1000.0);
        walletService.topUpWallet(wallet);
        double expected = 1000.0;
        Wallet mywallet = walletRepository.findById(wallet.getId()).get();
        double actual = mywallet.getBalance();
        assertTrue(expected==actual);
    }

    @Test
    void transactionlWallet() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);
        Wallet wallet = new Wallet();
        wallet.setBalance(15000.0);
        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);
        Double ammountTrx = 15000.0;
        Double ballance = wallet.getBalance();
        wallet.setBalance(ballance - ammountTrx);
        walletService.topUpWallet(wallet);

        Wallet mywallet = walletRepository.findById(wallet.getId()).get();
        double actual = mywallet.getBalance();
        assertEquals(0,mywallet.getBalance());
    }

    @Test
    void getWallet() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);
        Wallet wallet = new Wallet();
        wallet.setBalance(15000.0);
        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);
        walletService.topUpWallet(wallet);

        Wallet mywallet = walletRepository.findById(wallet.getId()).get();
        assertTrue(mywallet.getBalance()==15000.0);


    }

    @Test
    void isOwnerActive() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);
        Wallet wallet = new Wallet();
        wallet.setBalance(15000.0);
        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);
        Account expected = walletService.isOwnerActive(wallet);
        assertTrue(expected.getActive());
    }

    @Test
    void isWalletAlready() {
        Account account = new Account();
        account.setActive(true);
        accountService.saveAccount(account);
        this.wallet.setBalance(15000.0);
        this.wallet.setOwner(account);
        this.wallet = walletService.saveWallet(wallet);;
        assertThrows(AccountIsAlreadyHaveWalletException.class, ()->{
            walletService.isWalletAlready(this.wallet);
        });
    }

    @Test
    void shouldThrows_Exception_WhenWalletAccount_NotActive(){

        Account account = new Account();
        account.setActive(false);
        account = accountRepository.save(account);

        Wallet walletCheck = new Wallet();
        walletCheck.setBalance(5000.00);
        walletCheck.setOwner(account);
        walletCheck = walletRepository.save(walletCheck);

        Wallet finalWalletCheck = walletCheck;
        assertThrows(AccountIsNotActiveException.class,()->{
            walletService.isOwnerActive(finalWalletCheck);
        });
    }

    @Test
    void shouldThrows_Exception_WhenAccountAlready_HaveWallet(){
        Account account = new Account();
        account.setActive(false);
        account = accountRepository.save(account);

        Wallet walletCheck = new Wallet();
        walletCheck.setBalance(5000.00);
        walletCheck.setOwner(account);
        walletCheck = walletRepository.save(walletCheck);

        Wallet finalWalletCheck = walletCheck;
        assertThrows(AccountIsAlreadyHaveWalletException.class,()->{
            walletService.isWalletAlready(finalWalletCheck);
        });
    }
}