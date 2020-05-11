package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.ArtistRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistServiceDBImplTest {

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtistRepository artistRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void cleanUp(){
        artistRepository.deleteAll();
    }

    @Test
    void saveArtist_shouldAdd_1Data_inDB_whenArtistSaved() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonArtist = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        artistService.saveArtist(firstFile,jsonArtist);
        assertEquals(1, artistRepository.findAll().size());
    }

    @Test
    void saveArtist_should_return_id_NotNull() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonArtist = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Artist saveArtist = artistService.saveArtist(firstFile, jsonArtist);
        assertTrue(saveArtist.getId()!=null);
    }

    @Test
    void deleteArtist_shouldDelete_1_data_inDB_whenAlbumDeleted() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonArtist = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Artist saveArtist1 = artistService.saveArtist(firstFile, jsonArtist);
        Artist saveArtist2 = artistService.saveArtist(firstFile, jsonArtist);
        artistService.deleteArtist(saveArtist2.getId());
        assertEquals(1, artistRepository.findAll().size());
    }

    @Test
    void getAllArtist_shouldBe_2InDB_whenDataInDbIs_2() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonArtist = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Artist saveArtist1 = artistService.saveArtist(firstFile, jsonArtist);
        Artist saveArtist2 = artistService.saveArtist(firstFile, jsonArtist);
        assertEquals(2,artistRepository.findAll().size());
    }

    @Test
    void searcbArtistByField_shouldGetArtist_whenGivenSearchValue() throws IOException {
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments",fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonArtist = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        Artist saveArtist1 = artistService.saveArtist(firstFile, jsonArtist);
        assertEquals(1, artistService.searchArtist(PageRequest.of(0,5),saveArtist1).getTotalElements());
    }

    @Test
    void shouldThrow_Exception_WhenId_NotFound(){
        assertThrows(ResourceNotFoundException.class,()->{
           artistService.getArtist("there isn't id");
        });
    }

}