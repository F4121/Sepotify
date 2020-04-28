package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreServiceDBImplTest {

    @Autowired
    GenreService genreService;

    @Autowired
    GenreRepository genreRepository;

    Genre genre = new Genre();

    @BeforeEach
    public void cleanUp(){
        genreRepository.deleteAll();
    }

    @Test
    void saveGenre_shouldAdd_1Data_inDB_whenGenreSave(){
        genre.setName("Rock");
        genreService.saveGenre(genre);
        assertEquals(1, genreRepository.findAll().size());
    }

    @Test
    void saveGenre_should_return_id_NotNull(){
        genre.setName("Rock");
        Genre expected = genreService.saveGenre(genre);
        assertTrue(expected.getId()!=null);
    }

    @Test
    void deleteGenre_shouldDelete_1_datainDB_whenGenreDeleted(){
        Genre genretest1 = genreService.saveGenre(genre);
        genreService.deleteGenre(genretest1.getId());
        assertEquals(0, genreRepository.findAll().size());
    }

    @Test
    void getAllGenre_shouldBe_2InDB_whenDataInDbIs_2(){
        genre.setName("rock");
        genreService.saveGenre(genre);
        Genre genre1 = new Genre();
        genre1.setName("rock1");
        genreService.saveGenre(genre1);
        assertEquals(2, genreRepository.findAll().size());
    }

    @Test
    void getGenreByField_shouldGetGenre_whenGivenSearchValue(){
        genre.setName("rock");
        Genre genresearch = genreService.saveGenre(genre);
        assertEquals(1, genreService.searchGenre(PageRequest.of(0,5),genresearch).getTotalElements());
    }

}