package com.enigma.sepotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class MaxSizeException extends RuntimeException{
    public MaxSizeException(Integer size) {
        super("File should not greater than 1MB your file is " + size + " MB");
    }
}
