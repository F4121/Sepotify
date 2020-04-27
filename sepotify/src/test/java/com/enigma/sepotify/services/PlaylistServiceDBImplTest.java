package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Playlist;
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
class PlaylistServiceDBImplTest {

    @MockBean
    PlaylistService playlistService;

    Playlist playlist = new Playlist();

    @Test
    void savePlaylist_should_call_saveGenre_once() throws Exception {
        playlistService.savePlaylist(playlist);
        Mockito.verify(playlistService, Mockito.times(1)).savePlaylist(playlist);
    }

    @Test
    void getPlaylist_should_call_getPlaylist_once() throws Exception {
        playlistService.getPlaylist(playlist.getId());
        Mockito.verify(playlistService, Mockito.times(1)).getPlaylist(playlist.getId());
    }

    @Test
    void deletePlaylist_should_call_deletePlaylist_once() throws Exception {
        playlistService.deletePlaylist(playlist.getId());
        Mockito.verify(playlistService, Mockito.times(1)).deletePlaylist(playlist.getId());
    }

    @Test
    void searchPlaylist_should_call_searchPlaylist_once() throws Exception {
        playlistService.searchPlaylist(null,playlist);
        Mockito.verify(playlistService, Mockito.times(1)).searchPlaylist(null,playlist);
    }
}