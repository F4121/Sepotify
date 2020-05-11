package com.enigma.sepotify.repository;

import com.enigma.sepotify.entity.WalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletHistoryRepository extends JpaRepository<WalletHistory, String>, JpaSpecificationExecutor<WalletHistory> {
}
