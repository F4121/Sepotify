package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.WalletHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletHistoryService {
    public WalletHistory saveWalletHistory(WalletHistory walletHistory);
    public WalletHistory getWalletHistory(String id);
    public Page<WalletHistory> searchWalletHistory(Pageable pageable, WalletHistory searchForm);
    public void deleteWalletHistory(String id);
}
