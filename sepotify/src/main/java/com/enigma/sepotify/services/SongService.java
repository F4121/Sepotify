package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {
    public void saveSong(Song song);
    public Song getSong(String id);
    public void deleteSong(String id);
    public Page<Song> searchSong(Pageable pageable, Song searchForm);
}
