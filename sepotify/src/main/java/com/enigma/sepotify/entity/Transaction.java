package com.enigma.sepotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_transaction")
public class Transaction {

    @Id
    @GeneratedValue(generator = "transaction_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "transaction_uuid", strategy = "uuid")
    private String id;

    private Timestamp trxDate;
    private Double amount;
    private Double albumDiscount;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties(value = {"transactions"})
    private Song item;

    @Transient
    private String album;

    @Transient
    private List<Song> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    @JsonIgnoreProperties(value = {"transactions"})
    private Wallet wallet;

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Timestamp trxDate) {
        this.trxDate = trxDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAlbumDiscount() {
        return albumDiscount;
    }

    public void setAlbumDiscount(Double albumDiscount) {
        this.albumDiscount = albumDiscount;
    }

    public Song getItem() {
        return item;
    }

    public void setItem(Song item) {
        this.item = item;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<Song> getItems() {
        return items;
    }

    public void setItems(List<Song> items) {
        this.items = items;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
