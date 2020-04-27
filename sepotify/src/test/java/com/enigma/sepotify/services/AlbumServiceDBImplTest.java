package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.repository.AlbumRepository;
import com.enigma.sepotify.spesification.AlbumJpaSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumServiceDBImplTest {

    @MockBean
    AlbumService albumService;


    Pageable pageable = null;
    ObjectMapper objectMapper = new ObjectMapper();
    Album album = new Album("Telisik","First Album",2020,30.0);

//    @Test
//    void saveAlbum_should_response_OK200() throws Exception {
//        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
//
//        MockMultipartFile jsonFile = new MockMultipartFile("requestBody", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
//
//        albumService.saveAlbum(file,jsonFile.toString());
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/album")
//                .contentType(MediaType.IMAGE_JPEG_VALUE)
//                .content(objectMapper.writeValueAsString(album));
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//    }
//
//    @Test
//    void saveAlbum_should_callAlbumService_savealbum_once() throws Exception {
//        byte[] image = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("your_pic.jpg"));
//        MockMultipartFile file = new MockMultipartFile("file", "your_pic.jpg", MediaType.IMAGE_JPEG_VALUE, image);
//        MockMultipartFile jsonFile = new MockMultipartFile("requestBody", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
//
//        albumService.saveAlbum(file,jsonFile.toString());
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/album")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(album));
//        mockMvc.perform(requestBuilder);
//        Mockito.verify(albumService, Mockito.times(1)).saveAlbum(file, jsonFile.toString());
//    }


    @Test
    void getAlbum_should_call_getAlbum_once() throws Exception {
        Mockito.verify(albumService, Mockito.times(1)).getAlbum(album.getId());
    }

    @Test
    void searchAlbum_should_call_searchAlbum_once() throws Exception {
        Mockito.verify(albumService, Mockito.times(1)).searchAlbum(pageable,album);
    }

//    @Test
//    void searchAlbum_should_return_id_NotNull() throws Exception {
//        Page<Album> albums = albumRepository.findAll(AlbumJpaSpecification.findByCriterias(null), pageable);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/album")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(album));
//        mockMvc.perform(requestBuilder);
//        Mockito. when(albumService.searchAlbum(pageable, album)).thenReturn(album);
//        assertTrue(albums!=null);
//    }

    @Test
    void deleteAlbum_should_call_deleteAlbum_once() throws Exception {
        Mockito.verify(albumService, Mockito.times(1)).deleteAlbum(album.getId());
    }
}