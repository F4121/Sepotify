package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Playlist;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.PlaylistRepository;
import com.enigma.sepotify.spesification.PlaylistJpaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceDBImpl implements PlaylistService{

    @Autowired
    PlaylistRepository playlistRepository;

    @Override
    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public Playlist getPlaylist(String id) {
        Playlist playlist;
        if (playlistRepository.findById(id).isPresent()) playlist = playlistRepository.findById(id).get();
        else throw  new ResourceNotFoundException(id, Playlist.class);
        return playlist;
    }

    @Override
    public void deletePlaylist(String id) {
        if (playlistRepository.findById(id).isPresent()) playlistRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Genre.class);
    }

    @Override
    public Page<Playlist> searchPlaylist(Pageable pageable, Playlist searchForm) {
        Page<Playlist> playlists = playlistRepository.findAll(PlaylistJpaSpecification.findByCriterias(searchForm), pageable);
        return playlists;
    }
}
