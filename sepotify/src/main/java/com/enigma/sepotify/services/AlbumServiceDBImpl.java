package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.AlbumRepository;
import com.enigma.sepotify.spesification.AlbumJpaSpecification;
import com.enigma.sepotify.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class AlbumServiceDBImpl implements AlbumService{

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    FileUtil fileUtil;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveAlbum(MultipartFile file, String requestBody) throws JsonProcessingException, IOException {
        Album album = objectMapper.readValue(requestBody, Album.class);
        try{
            albumRepository.save(album);
            String destinaton = String.format("album/%s.%s",
                    album.getId().replaceAll("-",""),
                    FilenameUtils.getExtension(file.getOriginalFilename()));
            String path = fileUtil.store(file, destinaton);
            album.setImage(path);
            albumRepository.save(album);
        } catch (IOException ioe){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"something happened during file upload process");
        }
    }

    @Override
    public Album getAlbum(String id) {
        Album album;
        if (albumRepository.findById(id).isPresent()) album = albumRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Album.class);
        return album;
    }

    @Override
    public Page<Album> searchAlbum(Pageable pageable, Album searchForm) {
        Page<Album> albums = albumRepository.findAll(AlbumJpaSpecification.findByCriterias(searchForm), pageable);
        return albums;
    }

    @Override
    public void deleteAlbum(String id) {
        if (albumRepository.findById(id).isPresent()) albumRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Album.class);
    }
}
