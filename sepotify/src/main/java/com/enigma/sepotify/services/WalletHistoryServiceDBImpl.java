package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.WalletHistory;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.WalletHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WalletHistoryServiceDBImpl implements WalletHistoryService {

    @Autowired
    WalletHistoryRepository walletHistoryRepository;

    @Override
    public WalletHistory saveWalletHistory(WalletHistory walletHistory) {
        return walletHistoryRepository.save(walletHistory);
    }

    @Override
    public WalletHistory getWalletHistory(String id) {
        WalletHistory walletHistory;
        if (walletHistoryRepository.findById(id).isPresent()) walletHistory = walletHistoryRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, WalletHistory.class);
        return walletHistory;
    }

    @Override
    public Page<WalletHistory> searchWalletHistory(Pageable pageable, WalletHistory searchForm) {
        return null;
    }

    @Override
    public void deleteWalletHistory(String id) {
        if (walletHistoryRepository.findById(id).isPresent()) walletHistoryRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, WalletHistory.class);
    }
}
