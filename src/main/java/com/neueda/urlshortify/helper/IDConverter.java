package com.neueda.urlshortify.helper;

import org.springframework.stereotype.Component;

@Component
public class IDConverter  {

    private static final String POSSIBLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final long BASE = POSSIBLE_ALPHABET.length();


    /**
     * Function to generate a shortenKey from integer ID
     */

    public String encode(long id) {
        StringBuilder shortenKey = new StringBuilder();
        while (id > 0) {
            shortenKey.insert(0, POSSIBLE_ALPHABET.charAt((int) (id % BASE)));
            id = id / BASE;
        }
        return shortenKey.toString();
    }


}
