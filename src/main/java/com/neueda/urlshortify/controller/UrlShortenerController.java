package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.dto.KeyUrlDTO;
import com.neueda.urlshortify.exception.MalFormedURLException;
import com.neueda.urlshortify.service.UrlShortenerService;
import com.neueda.urlshortify.helper.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.net.URI;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/shortener")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService  urlShortenerService;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public ResponseEntity<KeyUrlDTO> shortener(@RequestBody OriginalUrlDTO dto) throws MalFormedURLException {
        if(StringUtils.isEmpty(dto.getOriginalUrl())){
            throw new MalFormedURLException(messageSource.getMessage(
                    "not.empty.or.null", null, new Locale("el")));
        };
        dto.setOriginalUrl(requestHelper.urlNormalization(dto.getOriginalUrl()));

        if(!requestHelper.isValid(dto.getOriginalUrl())){
            throw new MalFormedURLException(messageSource.getMessage(
                    "url.invalid", null, LocaleContextHolder.getLocale()));
        }

        return new ResponseEntity<KeyUrlDTO>(urlShortenerService.shortenUrl(dto.getOriginalUrl()), HttpStatus.CREATED);
    }

}
