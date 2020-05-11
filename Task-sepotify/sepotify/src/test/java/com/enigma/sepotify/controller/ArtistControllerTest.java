package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.services.ArtistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @MockBean
    ArtistController artistController;

    @MockBean
    ArtistService artistService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper =new ObjectMapper();

    @Test
    void getArtistById() throws Exception {
        Artist artist =  new Artist();
        artist.setId("123");
        artistController.getArtistById(artist.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artist/" + artist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteArtist() throws Exception {
        Artist artist =  new Artist();
        artist.setId("123");
        artistService.deleteArtist(artist.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/artist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchArtist() throws Exception {
        Artist artist =  new Artist();
        artist.setName("test");
        artistService.searchArtist(PageRequest.of(0, 10),artist);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/artist/search?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}