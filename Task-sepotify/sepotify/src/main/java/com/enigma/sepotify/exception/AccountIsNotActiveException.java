package com.enigma.sepotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountIsNotActiveException extends RuntimeException{
    public AccountIsNotActiveException(String id) {
        super("Resource with ID " + id +" is not active!");
    }
}
