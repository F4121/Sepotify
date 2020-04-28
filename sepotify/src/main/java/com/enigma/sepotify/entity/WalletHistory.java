package com.enigma.sepotify.entity;

import com.enigma.sepotify.enums.GenderEnum;
import com.enigma.sepotify.enums.HistoryTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_wallet_history")
public class WalletHistory {

    @Id
    @GeneratedValue(generator = "wallet_history_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "wallet_history_uuid", strategy = "uuid")
    private String id;
    //enums
    @Enumerated(EnumType.STRING)
    private HistoryTypeEnum type;
    private Double amount;
    private Timestamp trxDate;

    //relasi wallet
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    @JsonBackReference(value = "wallet")
//    @JsonIgnoreProperties(value = {"histories","transactions","owner"})
    private Wallet wallet;

    @Transient
    @JsonBackReference(value = "wallet-id")
    private String walletId;

    public WalletHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HistoryTypeEnum getType() {
        return type;
    }

    public void setType(HistoryTypeEnum type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Timestamp trxDate) {
        this.trxDate = trxDate;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }
}
