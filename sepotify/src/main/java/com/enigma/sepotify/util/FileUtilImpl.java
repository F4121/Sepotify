package com.enigma.sepotify.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtilImpl implements FileUtil{
    private final Path storageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    @Override
    public String store(MultipartFile file, String destination) throws IOException {
        Path target = storageLocation.resolve(destination);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return destination;
    }

    @Override
    public Resource read(String filename) throws FileNotFoundException {
        try{
            Path file = storageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()) throw new FileNotFoundException(filename);
            return resource;
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new FileNotFoundException(filename);
        }
    }
}
