package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceDBImplTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    Account account = new Account();

    @BeforeEach
    public void cleanUp(){
        accountRepository.deleteAll();
    }

    @Test
    void saveAccount_should_1_data_inDB_whenSaved() {
        account.setActive(true);
        accountService.saveAccount(account);
        assertEquals(1,accountRepository.findAll().size());
    }

    @Test
    void saveAccount_should_2_data_inDB_whenSaved() {
        account.setActive(true);
        accountService.saveAccount(account);
        Account account1 = new Account();
        account1.setActive(false);
        accountService.saveAccount(account1);
        assertEquals(2,accountRepository.findAll().size());
    }

    @Test
    void getAccount_should_not_Null() {
        account.setActive(true);
        Account expected = accountService.saveAccount(account);
        Account actual = accountRepository.findById(expected.getId()).get();
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void searchAccount() {
        account.setActive(true);
        account = accountService.saveAccount(account);
        Account actual = accountRepository.findById(account.getId()).get();
        assertTrue(actual.getActive()==true);
    }

    @Test
    void deleteAccount_should_delete1_data_inDB() {
        account.setActive(true);
        accountService.saveAccount(account);
        Account account1 = new Account();
        account1.setActive(false);
        accountService.saveAccount(account1);
        accountService.deleteAccount(account1.getId());
        assertEquals(1, accountRepository.findAll().size());
    }
}