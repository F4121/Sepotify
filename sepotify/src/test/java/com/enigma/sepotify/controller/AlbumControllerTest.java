package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.services.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.nio.charset.StandardCharsets;


@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @MockBean
    AlbumService albumService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAlbumById() throws Exception {
        Album album =  new Album();
        album.setId("123");
        albumService.getAlbum(album.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/album/"+album.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    void saveAlbum_should_() throws Exception {
//        Album album = new Album();
//
//        MockMultipartFile metadata =
//                new MockMultipartFile(
//                        "request",
//                        "request",
//                        MediaType.APPLICATION_JSON_VALUE,
//                        objectMapper.writeValueAsString(request).getBytes(StandardCharsets.UTF_8));
//
//        Mockito.when(albumService.saveAlbum(new ,requestBody)).thenReturn();
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/album")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(artist));
//        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    void deleteAlbum_should_response_200OK() throws Exception {
        Album album =  new Album();
        album.setId("123");
        albumService.deleteAlbum(album.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/album")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchAlbum() throws Exception {
        Album album =  new Album();
        album.setId("123");
        albumService.searchAlbum(null,album);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/album/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}