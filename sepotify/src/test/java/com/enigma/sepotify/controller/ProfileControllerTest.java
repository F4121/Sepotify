package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {

    @MockBean
    ProfileController profileController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getProfileById() throws Exception {
        Profile profile =  new Profile();
        profile.setId("123");
        profileController.getProfileById(profile.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/" + profile.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveProfile() throws Exception {
        Profile profile =  new Profile();
        profile.setId("123");
        profileController.saveProfile(profile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteProfile() throws Exception {
        Profile profile =  new Profile();
        profile.setId("123");
        profileController.deleteProfile(profile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}