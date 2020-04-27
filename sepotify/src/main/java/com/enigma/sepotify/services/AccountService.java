package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    public Account saveAccount(Account account);
    public Account getAccount(String id);
    public Page<Account> searchAccount(Pageable pageable, Account searchForm);
    public void deleteAccount(String id);
}
