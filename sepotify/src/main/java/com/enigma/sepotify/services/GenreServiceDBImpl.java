package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.GenreRepository;
import com.enigma.sepotify.spesification.GenreJpaSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceDBImpl implements GenreService{
    @Autowired
    GenreRepository genreRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public Genre getGenre(String id) {
        Genre genre;
        if (genreRepository.findById(id).isPresent()) genre = genreRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Genre.class);
        return genre;
    }

    @Override
    public void deleteGenre(String id) {
        if (genreRepository.findById(id).isPresent()) genreRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Genre.class);
    }

    @Override
    public Page<Genre> searchGenre(Pageable pageable, Genre searchForm) {
        Page<Genre> genres = genreRepository.findAll(GenreJpaSpecification.findByCriterias(searchForm), pageable);
        return genres;
    }
}
