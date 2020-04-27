package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Genre;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenreServiceDBImplTest {

    @MockBean
    GenreService genreService;

    Genre genre = new Genre();

    @Test
    void saveGenre_should_call_saveGenre_once() throws Exception {
        genreService.saveGenre(genre);
        Mockito.verify(genreService, Mockito.times(1)).saveGenre(genre);
    }

    @Test
    void getGenre_should_call_getGenre_once() throws Exception {
        genreService.getGenre(genre.getId());
        Mockito.verify(genreService, Mockito.times(1)).getGenre(genre.getId());
    }

    @Test
    void deleteGenre_should_call_deleteGenre_once() throws Exception {
        genreService.deleteGenre(genre.getId());
        Mockito.verify(genreService, Mockito.times(1)).deleteGenre(genre.getId());
    }

    @Test
    void searchGenre_should_call_deleteGenre_once() throws Exception {
        genreService.searchGenre(null,genre);
        Mockito.verify(genreService, Mockito.times(1)).searchGenre(null, genre);
    }
}