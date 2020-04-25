package com.enigma.sepotify.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_playlist")
public class Playlist {
    private String id;
    private String name;
    private Boolean isPublic;

    //relasi song
    @ManyToMany
	@JoinTable(name = "playlist_has_song",
	joinColumns = {@JoinColumn(name = "playlist_id")}
	,inverseJoinColumns = {@JoinColumn(name = "song_id")})
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    public Playlist() {
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }
}
