package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.entity.Genre;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {

    @MockBean
    GenreController genreController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getGenreById() throws Exception {
        Genre genre =  new Genre();
        genre.setName("test");
        genreController.getGenreById(genre.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genre/" + genre.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(genre));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveGenre() throws Exception {
        String jsonGenre = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonGenre);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteGenre() throws Exception {
        Genre genre = new Genre();
        genre.setId("1234");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchGenre() throws Exception {
        Genre genre = new Genre();
        genre.setName("test");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genre/search?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}