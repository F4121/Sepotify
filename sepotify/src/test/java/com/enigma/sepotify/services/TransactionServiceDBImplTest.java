package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.*;
import com.enigma.sepotify.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceDBImplTest {

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    WalletService walletService;

    @Autowired
    AlbumService albumService;

    @Autowired
    SongService songService;

    Transaction transaction = new Transaction();

    @BeforeEach
    public void cleanUp() {
        transactionRepository.deleteAll();
    }

    @Test
    void saveTransaction_should_1_data_in_DB() {
        transaction.setAmount(15000.0);

        Wallet wallet = new Wallet();
        wallet.setBalance(10000.0);

        Account account = new Account();
        account.setActive(true);
        account = accountService.saveAccount(account);

        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);

        transaction.setWallet(wallet);

        Song song = new Song();
        song.setTitle("test");
        song.setPrice(1500.0);
        song = songService.saveSong(song);
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        transaction.setItems(songs);
        transactionService.saveTransaction(transaction);

        assertEquals(1, transactionRepository.findAll().size());
    }

    @Test
    void saveAlbumTransaction() throws IOException {
        transaction.setAmount(15000.0);

        Wallet wallet = new Wallet();
        wallet.setBalance(10000.0);

        Account account = new Account();
        account.setActive(true);
        account = accountService.saveAccount(account);

        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);

        transaction.setWallet(wallet);

        Song song = new Song();
        song.setTitle("test");
        song.setPrice(1500.0);
        song = songService.saveSong(song);
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        Album album = new Album();
        album.setTitle("sendu");
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments", fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        album = albumService.saveAlbum(firstFile, jsonAlbum);
        ObjectMapper objectMapper = new ObjectMapper();
        transaction.setAlbum(objectMapper.writeValueAsString(album));
        transaction.setItems(songs);
        transactionService.saveTransaction(transaction);
    }

    @Test
    void getTransaction() throws IOException {
        transaction.setAmount(15000.0);

        Wallet wallet = new Wallet();
        wallet.setBalance(10000.0);

        Account account = new Account();
        account.setActive(true);
        account = accountService.saveAccount(account);

        wallet.setOwner(account);
        wallet = walletService.saveWallet(wallet);

        transaction.setWallet(wallet);

        Song song = new Song();
        song.setTitle("test");
        song.setPrice(1500.0);
        song = songService.saveSong(song);
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        Album album = new Album();
        album.setTitle("sendu");
        Resource fileResource = new ClassPathResource("/test/ff80818171b47fee0171b482324b0002.jpg");
        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments", fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        String jsonAlbum = " {\n" +
                "            \"title\": \"c\"\n" +
                "} ";
        album = albumService.saveAlbum(firstFile, jsonAlbum);
        ObjectMapper objectMapper = new ObjectMapper();
        transaction.setAlbum(objectMapper.writeValueAsString(album));
        transaction.setItems(songs);
        transactionService.saveTransaction(transaction);
        assertEquals(1, transactionRepository.findAll().size());
    }
}