package com.enigma.sepotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_account")
public class Account {

    @Id
    @GeneratedValue(generator = "account_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "account_uuid", strategy = "uuid")
    private String id;

    @JsonProperty
    private Boolean isActive;

    //relasi playlist
    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties(value = {"account"})
    private List<Playlist> playlists = new ArrayList<>();

    //relasi profile
    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties(value = {"account"})
    private Profile profile;

    @OneToOne(mappedBy = "owner")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnoreProperties(value = {"owner","histories","topUp","withdrawl","transactions"})
    private Wallet wallet;

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
