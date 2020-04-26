package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.entity.Genre;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GenreService {
    public void saveGenre(Genre genre);
    public Genre getGenre(String id);
    public void deleteGenre(String id);
    public Page<Genre> searchGenre(Pageable pageable, Genre searchForm);

}
