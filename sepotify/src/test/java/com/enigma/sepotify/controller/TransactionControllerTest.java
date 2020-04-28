package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Profile;
import com.enigma.sepotify.entity.Transaction;
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
class TransactionControllerTest {

    @MockBean
    TransactionController transactionController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getTransactionById() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId("123");
        transactionController.getTransactionById(transaction.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transaction/" + transaction.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId("123");
        transactionController.saveTransaction(transaction);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void saveAlbumTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId("123");
        transactionController.saveAlbumTransaction(transaction);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction/album")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}