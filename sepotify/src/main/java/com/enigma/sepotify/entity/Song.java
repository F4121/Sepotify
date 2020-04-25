package com.enigma.sepotify.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_song")
public class Song {

    @Id
    @GeneratedValue(generator = "song_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "song_uuid", strategy = "uuid")
    private String id;
    private String title;
    private Integer releaseYear;
    private Integer duration;

    //relasi genre
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    //relasi album
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    //relasi artist
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private Double price;

    //relasi playlist
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();

    //relasi transaction
    @OneToMany(mappedBy = "item")
    private List<Transaction> transactions = new ArrayList<>();

    public Song() {
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
