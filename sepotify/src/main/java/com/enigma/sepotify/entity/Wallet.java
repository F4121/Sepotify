package com.enigma.sepotify.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_wallet")
public class Wallet {

    @Id
    @GeneratedValue(generator = "wallet_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "wallet_uuid", strategy = "uuid")
    private String id;
    private Double balance;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account owner;

    //relasi transactions
    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions = new ArrayList<>();

    //relasi wallethistory
    @OneToMany(mappedBy = "wallet")
    private List<WalletHistory> histories = new ArrayList<>();

    public Wallet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<WalletHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<WalletHistory> histories) {
        this.histories = histories;
    }
}
