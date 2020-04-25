package com.enigma.sepotify.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_playlist")
public class Playlist {

    @Id
    @GeneratedValue(generator = "playlist_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "playlist_uuid", strategy = "uuid")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }
}
