package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.dto.KeyUrlDTO;
import com.neueda.urlshortify.dto.UrlResponseDTO;
import com.neueda.urlshortify.exception.KeyNotFoundException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/redirect")
public class UrlRedirectController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{key}")
    public ResponseEntity<UrlResponseDTO> redirectUrl(@PathVariable String key, HttpServletRequest request) throws KeyNotFoundException {
        ResolveOriginalUrlDTO dto = ResolveOriginalUrlDTO.builder()
                .key(key)
                .browser(requestHelper.getBrowserType(request))
                .os(requestHelper.getOperatingSystemType(request))
                .build();
        UrlResponseDTO responseDTO = urlShortenerService.getOriginalUrl(dto);
        if (responseDTO == null) {
            throw new KeyNotFoundException(messageSource.getMessage(
                    "key.not.found", null, LocaleContextHolder.getLocale()));
        }
        return new ResponseEntity<UrlResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
