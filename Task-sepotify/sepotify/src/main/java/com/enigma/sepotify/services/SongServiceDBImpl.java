package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Song;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.SongRepository;
import com.enigma.sepotify.spesification.SongJpaSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SongServiceDBImpl implements SongService{

    @Autowired
    SongRepository songRepository;

    @Override
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song getSong(String id) {
        Song song;
        if (songRepository.findById(id).isPresent()){
            song = songRepository.findById(id).get();
        }else{
            throw new ResourceNotFoundException(id, Song.class);
        }
        return song;
    }

    @Override
    public void deleteSong(String id) {
        if (songRepository.findById(id).isPresent()) songRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Song.class);
    }

    @Override
    public Page<Song> searchSong(Pageable pageable, Song searchForm) {
        Page<Song> songs = songRepository.findAll(SongJpaSpesification.findByCriterias(searchForm), pageable);
        return songs;
    }
}
