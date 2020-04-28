package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Genre;
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
class AccountControllerTest {

    @MockBean
    AccountController accountController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAccountById() throws Exception {
        Account account =  new Account();
        account.setId("test");
        accountController.getAccountById(account.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/" + account.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(account));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveAccount() throws Exception {
        Account account =  new Account();
        accountController.saveAccount(account);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteAccount() throws Exception {
        Account account =  new Account();
        accountController.deleteAccount(account);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchAlbum() throws Exception {
        Account account =  new Account();
        account.setActive(true);
        accountController.searchAlbum(0,10,account);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/search?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}