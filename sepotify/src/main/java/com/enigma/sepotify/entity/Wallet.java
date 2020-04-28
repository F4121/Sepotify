package com.enigma.sepotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

    @Transient
    @JsonBackReference(value = "topUp-wallet")
    private Double topUp;

    @Transient
    @JsonBackReference(value = "withdrawl-wallet")
    private Double withdrawl;

    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties(value = {"playlists","profile","wallet","active"})
    private Account owner;

    //relasi transactions
    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties(value = {"wallet","item"})
    private List<Transaction> transactions = new ArrayList<>();

    //relasi wallethistory
    @OneToMany(mappedBy = "wallet")
    @JsonBackReference(value = "histories")
//    @JsonIgnoreProperties(value = {"wallet"})
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

    public Double getTopUp() {
        return topUp;
    }

    public void setTopUp(Double topUp) {
        this.topUp = topUp;
    }

    public Double getWithdrawl() {
        return withdrawl;
    }

    public void setWithdrawl(Double withdrawl) {
        this.withdrawl = withdrawl;
    }
}
