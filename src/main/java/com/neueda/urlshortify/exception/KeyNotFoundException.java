package com.neueda.urlshortify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KeyNotFoundException extends Exception {

    public KeyNotFoundException(String message){
        super(message);
    }
}
