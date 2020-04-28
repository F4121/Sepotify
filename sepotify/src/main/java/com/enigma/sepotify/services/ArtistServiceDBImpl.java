package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.exception.MaxSizeException;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.ArtistRepository;
import com.enigma.sepotify.spesification.ArtistJpaSpecification;
import com.enigma.sepotify.util.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class ArtistServiceDBImpl implements ArtistService{

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    FileUtil fileUtil;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Artist saveArtist(MultipartFile file, String requestBody) throws IOException {
        Artist artist = objectMapper.readValue(requestBody, Artist.class);
        Integer size = Math.toIntExact(file.getSize());
        if (size > 1048576){
            throw new MaxSizeException(size);
        }else {
            try{
                artistRepository.save(artist);
                String destination = String.format("artists/%s.%s",
                        artist.getId().replaceAll("-", ""),
                        FilenameUtils.getExtension(file.getOriginalFilename()));
                String path = fileUtil.store(file, destination);
                artist.setPhoto(path);
               return artistRepository.save(artist);
            }catch (IOException ieo){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something happened during file upload process");
            }
        }
    }

    @Override
    public Artist getArtist(String id) {
        Artist artist;
        if (artistRepository.findById(id).isPresent()) {
            artist = artistRepository.findById(id).get();
        }else{
            throw new ResourceNotFoundException(id, Artist.class);
        }
        return artist;
    }

    @Override
    public Page<Artist> searchArtist(Pageable pageable, Artist searchForm) {
        Page<Artist> artists = artistRepository.findAll(ArtistJpaSpecification.findByCriterias(searchForm), pageable);
        return artists;
    }

    @Override
    public void deleteArtist(String id) {
        if (artistRepository.findById(id).isPresent()) artistRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Artist.class);
    }
}
