package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Playlist;
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
class PlaylistControllerTest {

    @MockBean
    PlaylistController playlistController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getPlaylistById() throws Exception {
        Playlist playlist =  new Playlist();
        playlist.setName("test");
        playlistController.getPlaylistById(playlist.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/playlist/" + playlist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playlist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void savePlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setName("test");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/playlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playlist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deletePlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setId("test");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/playlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playlist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchPlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setName("test");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/playlist?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playlist));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}