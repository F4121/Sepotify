package com.enigma.sepotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SongAlreadyPurchasedException extends RuntimeException{
    public SongAlreadyPurchasedException(){
        super("Song already purchased!");
    }
}
