package com.enigma.sepotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String id, Class clazz) {
        super("Resource with ID " + id +" of " + clazz +" NotFound");
    }
}
