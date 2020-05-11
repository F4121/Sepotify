package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.AccountRepository;
import com.enigma.sepotify.spesification.AccountJpaSpecification;
import com.enigma.sepotify.spesification.AlbumJpaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceDBImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String id) {
        Account account;
        if (accountRepository.findById(id).isPresent()) account = accountRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Account.class);
        return account;
    }

    @Override
    public Page<Account> searchAccount(Pageable pageable, Account searchForm) {
        Page<Account> accounts = accountRepository.findAll(AccountJpaSpecification.findByCriterias(searchForm),pageable);
        return accounts;
    }

    @Override
    public void deleteAccount(String id) {
        if (accountRepository.findById(id).isPresent()) accountRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Account.class);
    }
}
