package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.service.UrlShortenerService;
import com.neueda.urlshortify.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/short")
public class UrlRedirectController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/{key}")
    public String redirectUrl(@PathVariable String key, HttpServletRequest request){
        ResolveOriginalUrlDTO dto = ResolveOriginalUrlDTO.builder()
                .shortUrl(key)
                .browser(RequestUtil.getBrowserType(request))
                .os(RequestUtil.getOperatingSystemType(request))
                .build();
        return urlShortenerService.getOriginalUrl(dto);
    }
}
