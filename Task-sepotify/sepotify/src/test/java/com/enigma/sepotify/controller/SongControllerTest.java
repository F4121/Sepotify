package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Song;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SongControllerTest {

    @MockBean
    SongController songController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getSongById() throws Exception {
        Song song =  new Song();
        song.setId("test");
        song.setDuration(250);
        songController.getSongById(song.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/song/" + song.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveSong() throws Exception {
        Song song =  new Song();
        song.setId("test");
        song.setDuration(250);
        songController.saveSong(song);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/song")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteSong() throws Exception {
        Song song =  new Song();
        song.setId("test");
        song.setDuration(250);
        songController.deleteSong(song);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/song")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchSong() throws Exception {
        Song song =  new Song();
        song.setId("test");
        song.setDuration(250);
        songController.searchSong(0,10,song);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/song/search?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}