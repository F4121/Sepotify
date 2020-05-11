package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Playlist;
import com.enigma.sepotify.repository.PlaylistRepository;
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
class PlaylistServiceDBImplTest {

    @Autowired
    PlaylistService playlistService;

    @Autowired
    PlaylistRepository playlistRepository;

    Playlist playlist = new Playlist();

    @BeforeEach
    public void cleanUp(){
        playlistRepository.deleteAll();
    }

    @Test
    void savePlaylist_shouldAdd_1Data_inDB_whenPlaylistSaved(){
        playlist.setId("123");
        playlist.setName("nyantai");
        playlistService.savePlaylist(playlist);
        assertEquals(1,playlistRepository.findAll().size());
    }

    @Test
    void savePlaylist_should_return_id_notNull(){
        playlist.setId("123");
        playlist.setName("nyantai");
        Playlist expected = playlistService.savePlaylist(playlist);
        assertTrue(expected.getId()!=null);
    }

    @Test
    void deletePlaylist_should_delete_1_data(){
        playlist.setId("123");
        playlist.setName("nyantai");
        Playlist expected = playlistService.savePlaylist(playlist);
        playlistService.deletePlaylist(expected.getId());
        assertEquals(0,playlistRepository.findAll().size());
    }

    @Test
    void getAll_should_2InDb_whenDataInDb_2(){
        playlist.setId("123");
        playlist.setName("nyantai");
        playlistService.savePlaylist(playlist);
        playlistService.savePlaylist(playlist);
        assertEquals(2, playlistRepository.findAll().size());
    }

    @Test
    void getPlaylistByField_shouldGetPlaylist_whenGivenSearchValue(){
        playlist.setId("123");
        playlist.setName("nyantai");
        playlistService.savePlaylist(playlist);
        playlistService.searchPlaylist(PageRequest.of(0,5),playlist).getTotalElements();

    }

}