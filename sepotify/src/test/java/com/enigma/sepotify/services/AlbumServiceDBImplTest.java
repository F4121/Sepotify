package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.repository.AlbumRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AlbumServiceDBImplTest {

    @Autowired
    AlbumService albumService;

    @Autowired
    AlbumRepository albumRepository;

    Pageable pageable = PageRequest.of(0, 10);
    Album album = new Album("Telisik","First Album",2020,30.0);

    Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
    MockMultipartFile firstFile = new MockMultipartFile(
            "attachments",fileResource.getFilename(),
            MediaType.MULTIPART_FORM_DATA_VALUE,
            fileResource.getInputStream());

    AlbumServiceDBImplTest() throws IOException {
    }

    @Test
    void saveAlbum_should_call_saveAlbum_once() throws Exception {
        MockMultipartFile jsonFile = new MockMultipartFile("requestBody", "", "application/json", "{\"name\": \"test\"}".getBytes());
        albumService.saveAlbum(firstFile,jsonFile.toString());
        Mockito.verify(albumService, Mockito.times(1)).saveAlbum(firstFile, jsonFile.toString());
    }

    @Test
    void saveAlbum_ShouldCreate_1_Album_in_DB_when_an_Album_Saved() throws IOException {
        String album = "{\n" +
                "            \"title\": \"Capitol\",\n" +
                "            \"description\": \"Frank wow yay!\",\n" +
                "            \"releaseYear\": 1954,\n" +
                "            \"discount\": 10.0\n" +
                "}";
        albumService.saveAlbum(firstFile, album);
        List<Album> albums = new ArrayList<>();
        albums.add(this.album);
        assertEquals(1,albumRepository.findAll().size());
    }

    @Test
    void saveAlbum_should_callAlbumService_savealbum_once() throws Exception {
        byte[] image = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("your_pic.jpg"));
        MockMultipartFile file = new MockMultipartFile("file", "application.properties", MediaType.IMAGE_JPEG_VALUE, image);
        MockMultipartFile jsonFile = new MockMultipartFile("requestBody", "", "application/json", "{\"json\": \"someValue\"}".getBytes());

        albumService.saveAlbum(file,jsonFile.toString());
        Mockito.verify(albumService, Mockito.times(1)).saveAlbum(file, jsonFile.toString());
    }


    @Test
    void getAlbum_should_call_getAlbum_once() throws Exception {
        albumService.getAlbum(album.getId());
        Mockito.verify(albumService, Mockito.times(1)).getAlbum(album.getId());
    }

    @Test
    void searchAlbum_should_call_searchAlbum_once() throws Exception {
        albumService.searchAlbum(pageable, album);
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
        albumService.deleteAlbum(album.getId());
        Mockito.verify(albumService, Mockito.times(1)).deleteAlbum(album.getId());
    }
}