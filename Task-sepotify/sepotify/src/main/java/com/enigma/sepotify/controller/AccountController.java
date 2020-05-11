package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable String id){
        return accountService.getAccount(id);
    }

    @PostMapping
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @DeleteMapping
    public void deleteAccount(@RequestBody Account account){
        accountService.deleteAccount(account.getId());
    }

    @GetMapping("/search")
    public Page<Account> searchAlbum(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestBody Account searchForm){
        Pageable pageable = PageRequest.of(page, size);
        return accountService.searchAccount(pageable, searchForm);
    }
}
