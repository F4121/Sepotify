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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
//    void saveAlbum_should_response_200OK() throws Exception {
//        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
//        MockMultipartFile firstFile = new MockMultipartFile(
//                "attachments",fileResource.getFilename(),
//                MediaType.MULTIPART_FORM_DATA_VALUE,
//                fileResource.getInputStream());
//
//        String jsonAlbum = " {\n" +
//                "            \"title\": \"c\"\n" +
//                "} ";
//        Album album = new Album();
//        Mockito.when(albumService.saveAlbum(firstFile,jsonAlbum)).thenReturn(album);
//        albumService.saveAlbum(firstFile,jsonAlbum);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/album")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .content(objectMapper.writeValueAsString(jsonAlbum));
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
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/album/search?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}