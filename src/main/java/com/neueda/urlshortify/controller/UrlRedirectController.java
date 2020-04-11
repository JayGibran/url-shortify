package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.service.UrlShortenerService;
import com.neueda.urlshortify.helper.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/redirect")
public class UrlRedirectController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Autowired
    private RequestHelper requestHelper;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/{key}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String key, HttpServletRequest request){
        ResolveOriginalUrlDTO dto = ResolveOriginalUrlDTO.builder()
                .shortUrl(key)
                .browser(requestHelper.getBrowserType(request))
                .os(requestHelper.getOperatingSystemType(request))
                .build();
        String originalUrl = urlShortenerService.getOriginalUrl(dto);
        if (originalUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders respHeader = new HttpHeaders();
        respHeader.setLocation(URI.create(originalUrl));
        respHeader.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate");
        ResponseEntity<Void> resp = new ResponseEntity<>(respHeader, HttpStatus.FOUND);
        return resp;
    }
}
