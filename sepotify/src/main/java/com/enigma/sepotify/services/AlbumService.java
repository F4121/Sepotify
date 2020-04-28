package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlbumService {
    public Album saveAlbum(MultipartFile file, String requestBody) throws JsonProcessingException, IOException;
    public Album getAlbum(String id);
    public Page<Album> searchAlbum(Pageable pageable, Album searchForm);
    public void deleteAlbum(String id);
}
