package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Song;
import com.enigma.sepotify.repository.SongRepository;
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
@AutoConfigureMockMvc
class SongServiceDBImplTest {

    @Autowired
    SongService songService;

    @Autowired
    SongRepository songRepository;

    Song song = new Song();

    @BeforeEach
    public void cleanUp(){
        songRepository.deleteAll();
    }

    @Test
    void saveSong_shouldAdd_1Data_inDB_whenSongsaved(){
        song.setTitle("coba");
        songService.saveSong(song);
        assertEquals(1, songRepository.findAll().size());
    }

    @Test
    void saveSong_should_return_id_NotNull(){
        song.setTitle("coba");
        Song expected = songService.saveSong(song);
        assertTrue(expected.getId()!=null);
    }

    @Test
    void deleteSong_shouldDelete_1_data_inDB_whenSongDeleted(){
        song.setTitle("coba");
        Song expected = songService.saveSong(song);
        songService.deleteSong(song.getId());
        assertEquals(0, songRepository.findAll().size());
    }

    @Test
    void getAllSong_shouldBe_2InDB_whenDataInDBIs2(){
        song.setTitle("coba");
        Song save1 = songService.saveSong(song);
        Song song1 = new Song();
        song.setTitle("coba2");
        Song save2 = songService.saveSong(song1);
        assertEquals(2, songRepository.findAll().size());
    }

//    @Test
//    void searchSongField_shouldGetSong_whenGivenSearchValue(){
//        song.setTitle("coba");
//        songService.saveSong(song);
//        assertEquals(1,songService.searchSong(PageRequest.of(0,5),song).getTotalElements());
//    }

}