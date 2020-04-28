package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Transaction;
import com.enigma.sepotify.entity.Wallet;
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
class WalletControllerTest {

    @MockBean
    WalletController walletController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getWalletById() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setId("123");
        walletController.getWalletById(wallet.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallet/" + wallet.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wallet));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveWallet() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setBalance(1500.0);
        walletController.saveWallet(wallet);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wallet));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void topUpWallet() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setBalance(1800.0);
        walletController.topUpWallet(wallet);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallet/topup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wallet));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void withDrawlWallet() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setBalance(1500.0);
        walletController.withDrawlWallet(wallet);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallet/withdrawl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wallet));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteWallet() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setId("123");
        walletController.deleteWallet(wallet);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wallet));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}