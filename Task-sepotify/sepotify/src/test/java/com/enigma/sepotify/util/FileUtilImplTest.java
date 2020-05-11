package com.enigma.sepotify.util;

import com.enigma.sepotify.exception.CustomFileNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileUtilImplTest {

    @Autowired
    FileUtil fileUtil;

    @Test
    void should_return_fileNotFound(){
        String filename = "asal";
        assertThrows(CustomFileNotFoundException.class,()->{
            fileUtil.read(filename);
        });
    }

}