package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Artist;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ArtistService {
    public Artist saveArtist(MultipartFile file, String requestBody) throws JsonProcessingException, IOException;
    public Artist getArtist(String id);
    public Page<Artist> searchArtist(Pageable pageable, Artist searchForm);
    public void deleteArtist(String id);
}
