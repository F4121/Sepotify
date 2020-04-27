package com.enigma.sepotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountIsAlreadyHaveWalletException extends RuntimeException{
    public AccountIsAlreadyHaveWalletException(String id){
        super("Resource with ID " + id +" is already have a wallet!");
    }
}
