package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {
    public void savePlaylist(Playlist playlist);
    public Playlist getPlaylist(String id);
    public void deletePlaylist(String id);
    public Page<Playlist> searchPlaylist(Pageable pageable, Playlist searchForm);

}
