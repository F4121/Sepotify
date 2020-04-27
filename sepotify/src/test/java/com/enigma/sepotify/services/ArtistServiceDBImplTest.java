package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistServiceDBImplTest {

    @MockBean
    ArtistService artistService;

    Artist artist = new Artist();
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void saveArtist() {

    }

    @Test
    void getArtist_should_call_getArtist_once() throws Exception {
        artist.setId("123");
        artist.setName("Test");
        Artist artist1 = artistService.getArtist(artist.getId());
        Mockito.verify(artistService, Mockito.times(1)).getArtist(artist.getId());
    }

    @Test
    void searchArtist_should_call_searchArtist_once() throws Exception {
        artistService.searchArtist(null, artist);
        Mockito.verify(artistService, Mockito.times(1)).searchArtist(null,artist);
    }

    @Test
    void deleteArtist_should_call_deleteArtist_once() throws Exception {
        artist.setId("test");
        artistService.deleteArtist(artist.getId());
        Mockito.verify(artistService, Mockito.times(1)).deleteArtist(artist.getId());
    }
}