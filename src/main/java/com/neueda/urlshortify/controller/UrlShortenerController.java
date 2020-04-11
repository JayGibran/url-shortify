package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.exception.UrlShortenerException;
import com.neueda.urlshortify.service.UrlShortenerService;
import com.neueda.urlshortify.helper.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/api/v1/shortener")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService  urlShortenerService;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired
    private MessageSource messageSource;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping
    public ResponseEntity<String> shortener(@RequestBody OriginalUrlDTO dto) throws UrlShortenerException {
        if(StringUtils.isEmpty(dto.getOriginalUrl())){
            return new ResponseEntity<>(
             messageSource.getMessage(
                     "not.empty.or.null", null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
        };

        if(!requestHelper.isValid(dto.getOriginalUrl())){
            return new ResponseEntity<>(
                    messageSource.getMessage(
                            "url.invalid", null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
        }

        String url = requestHelper.urlNormalization(dto.getOriginalUrl());

        return new ResponseEntity<>(urlShortenerService.shortenUrl(url), HttpStatus.OK);
    }

}
