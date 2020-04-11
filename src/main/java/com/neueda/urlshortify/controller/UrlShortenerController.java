package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.service.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shortener")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService  urlShortenerService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping
    public String shortener(@RequestBody OriginalUrlDTO dto) {
        return urlShortenerService.shortenUrl(dto.getOriginalUrl());
    }

}
