package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Song;
import com.enigma.sepotify.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id){
        return songService.getSong(id);
    }

    @PostMapping
    public void saveSong(@RequestBody Song song){
        songService.saveSong(song);
    }

    @DeleteMapping
    public void deleteSong(@RequestBody Song song){
        songService.deleteSong(song.getId());
    }

    @GetMapping("/search")
    public Page<Song> searchSong(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestBody Song searchform){
        Pageable pageable =PageRequest.of(page, size);
        return songService.searchSong(pageable, searchform);
    }
}
