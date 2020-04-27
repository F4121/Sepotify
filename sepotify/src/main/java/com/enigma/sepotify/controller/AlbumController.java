package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping("/{id}")
    public Album getArtistById(@PathVariable String id){
        return albumService.getAlbum(id);
    }

    @PostMapping
    public void saveAlbum(@RequestPart MultipartFile file, @RequestPart String requestBody) throws IOException {
        albumService.saveAlbum(file, requestBody);
    }

    @DeleteMapping
    public void deleteAlbum(@RequestBody Album album){
        albumService.deleteAlbum(album.getId());
    }

    @GetMapping("/search")
    public Page<Album> searchAlbum(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestBody Album searchForm){
        Pageable pageable = PageRequest.of(page, size);
        return albumService.searchAlbum(pageable, searchForm);
    }
}