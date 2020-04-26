package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable String id){
        return artistService.getArtist(id);
    }

    @PostMapping
    public void saveArtist(@RequestPart MultipartFile file, @RequestPart String requestBody) throws IOException {
        artistService.saveArtist(file, requestBody);
    }

    @DeleteMapping
    public void deleteArtist(@RequestBody Artist artist){
        artistService.deleteArtist(artist.getId());
    }

    @GetMapping("/search")
    public Page<Artist> searchArtist(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestBody Artist searchForm){
        Pageable pageable = PageRequest.of(page, size);
        return artistService.searchArtist(pageable, searchForm);
    }

//    @GetMapping("/pageku")
//    public Page<Artist> searchArtistBySongTitle(@RequestParam(name = "page") Integer page,@RequestParam(name = "size") Integer size, @RequestBody Artist searchForm){
//        Pageable pageable = PageRequest.of(page, size);
//        return artistService.searchArtistBySongTitle(pageable, searchForm);
//    }

}
