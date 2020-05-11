package com.enigma.sepotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_genre")
public class Genre {

    @Id
    @GeneratedValue(generator = "genre_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "genre_uuid", strategy = "uuid")
    private String id;

    private String name;

    //relasi song
    @OneToMany(mappedBy = "genre")
    private List<Song> songs = new ArrayList<>();

    public Genre() {
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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
