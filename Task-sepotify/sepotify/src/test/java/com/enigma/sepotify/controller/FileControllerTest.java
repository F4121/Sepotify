package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.entity.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

    @MockBean
    FileController fileController;

    @Autowired
    MockMvc mockMvc;
    HttpServletRequest request = null;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getArtistsPhotos() throws Exception {
        Artist artist = new Artist();
        artist.setId("123");
        fileController.getArtistsPhotos(artist.getId(),request);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artists/photos/" + artist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAlbumPhotos() throws Exception {
        Album album = new Album();
        album.setId("123");
        fileController.getAlbumPhotos(album.getId(),request);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/album/photos/" + album.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}