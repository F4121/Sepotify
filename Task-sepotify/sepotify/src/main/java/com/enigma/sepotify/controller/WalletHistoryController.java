package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.WalletHistory;
import com.enigma.sepotify.services.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet-history")
public class WalletHistoryController {

    @Autowired
    WalletHistoryService walletHistoryService;

    @GetMapping
    public Page<WalletHistory> getWalletHistoyByWalletId(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestBody WalletHistory searchform){
        Pageable pageable = PageRequest.of(page, size);
        return walletHistoryService.searchWalletHistory(pageable, searchform);
    }

}
