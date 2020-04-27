package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Song;
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
class SongServiceDBImplTest {

    @MockBean
    SongService songService;

    Song song = new Song();

    @Test
    void saveSong() throws Exception {
        songService.saveSong(song);
        Mockito.verify(songService, Mockito.times(1)).saveSong(song);
    }

    @Test
    void getSong_should_call_getSong_once() throws Exception {
        songService.getSong(song.getId());
        Mockito.verify(songService, Mockito.times(1)).getSong(song.getId());
    }

    @Test
    void deleteSong_should_call_getSong_once() throws Exception {
        songService.deleteSong(song.getId());
        Mockito.verify(songService, Mockito.times(1)).deleteSong(song.getId());
    }

    @Test
    void searchSong_should_call_getSong_once() throws Exception {
        songService.searchSong(null,song);
        Mockito.verify(songService, Mockito.times(1)).searchSong(null, song);
    }
}