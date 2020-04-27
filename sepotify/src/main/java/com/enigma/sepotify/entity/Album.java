package com.enigma.sepotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_album")
public class Album {

    @Id
    @GeneratedValue(generator = "album_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "album_uuid", strategy = "uuid")
    private String id;
    private String title;
    private String description;
    private Integer releaseYear;

    //relasi song
    @OneToMany(mappedBy = "album")
    @JsonIgnoreProperties(value = {"album"})
    private List<Song> songs = new ArrayList<>();

    private Double discount;
    private String image;

    public Album() {

    }

    public Album(String title, String description, Integer releaseYear, Double discount) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
