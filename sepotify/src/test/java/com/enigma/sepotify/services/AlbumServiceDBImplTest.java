package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.repository.AlbumRepository;
import com.enigma.sepotify.spesification.AlbumJpaSpecification;
import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
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

    @BeforeEach
    public void cleanUp(){
        albumRepository.deleteAll();
    }

    @Test
    void getAlbumByField_shouldGetAlbum_whenGivenSearchValue() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Album album1 = albumService.saveAlbum(firstFile, jsonAlbum);
        albumRepository.save(album1);
        assertEquals(1, albumService.searchAlbum(PageRequest.of(0,5),album1).getTotalElements());
    }

    @Test
    void getAllSong_shouldBe_2InDB_whenDataInDbIs_2() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Album album1 = albumService.saveAlbum(firstFile, jsonAlbum);
        Album album2 = albumService.saveAlbum(firstFile, jsonAlbum);
        assertEquals(2, albumRepository.findAll().size());
    }

    @Test
    void deleteAlbum_shouldDelete_1_data_inDb_whenAlbumDeleted() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Album album1 = albumService.saveAlbum(firstFile, jsonAlbum);
        Album album2 = albumService.saveAlbum(firstFile, jsonAlbum);
        albumService.deleteAlbum(album2.getId());
        assertEquals(1, albumRepository.findAll().size());
    }

    @Test
    void saveAlbum_shouldAdd_1_data_inDB_whenAlbumSaved() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Album expected = albumService.saveAlbum(firstFile, jsonAlbum);
        assertEquals(1,albumRepository.findAll().size());
    }

    @Test
    void searchAlbum_should_return_id_NotNull() throws Exception {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Album saveAlbum = albumService.saveAlbum(firstFile, jsonAlbum);
        assertTrue(saveAlbum.getId()!=null);
    }




}