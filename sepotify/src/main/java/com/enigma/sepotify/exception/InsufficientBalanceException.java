package com.enigma.sepotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){
        super("You don't have enough balance in your wallet!");
    }
}
