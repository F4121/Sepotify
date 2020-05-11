package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.services.AlbumService;
import com.enigma.sepotify.services.ArtistService;
import com.enigma.sepotify.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    FileUtil fileUtil;
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;

    public FileController(FileUtil fileUtil, ArtistService artistService) {
        this.fileUtil = fileUtil;
        this.artistService = artistService;
    }

    @GetMapping("/artists/photos/{id}")
    public ResponseEntity<Resource> getArtistsPhotos(@PathVariable String id, HttpServletRequest request) throws FileNotFoundException {
        Artist artist = artistService.getArtist(id);
        if (artist == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        Resource resource = fileUtil.read(artist.getPhoto());
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/album/photos/{id}")
    public ResponseEntity<Resource> getAlbumPhotos(@PathVariable String id, HttpServletRequest request) throws FileNotFoundException {
        Album album = albumService.getAlbum(id);
        if (album == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        Resource resource = fileUtil.read(album.getImage());
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
