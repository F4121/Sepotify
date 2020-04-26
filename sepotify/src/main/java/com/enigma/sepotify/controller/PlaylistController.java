package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Playlist;
import com.enigma.sepotify.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable String id){
        return playlistService.getPlaylist(id);
    }

    @PostMapping
    public void savePlaylist(@RequestBody Playlist playlist){
        playlistService.savePlaylist(playlist);
    }

    @DeleteMapping
    public void deletePlaylist(@RequestBody Playlist playlist){
        playlistService.deletePlaylist(playlist.getId());
    }

    @GetMapping("/search")
    public Page<Playlist> searchPlaylist(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size,@RequestBody Playlist searchform){
        Pageable pageable = PageRequest.of(page, size);
        return playlistService.searchPlaylist(pageable, searchform);
    }
}
