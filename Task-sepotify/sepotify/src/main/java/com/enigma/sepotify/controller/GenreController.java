package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable String id){
        return genreService.getGenre(id);
    }

    @PostMapping
    public void saveGenre(@RequestBody Genre genre){
        genreService.saveGenre(genre);
    }

    @DeleteMapping
    public void deleteGenre(@RequestBody Genre genre){
        genreService.deleteGenre(genre.getId());
    }

    @PostMapping("/search")
    public Page<Genre> searchGenre(@RequestParam(name = "page", defaultValue ="0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size,@RequestBody Genre searchform){
        Pageable pageable = PageRequest.of(page, size);
        return genreService.searchGenre(pageable, searchform);
    }
}
