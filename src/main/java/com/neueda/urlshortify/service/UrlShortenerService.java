package com.neueda.urlshortify.service;

import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;

public interface UrlShortenerService {

    public String shortenUrl(String originalUrl);

    public String getOriginalUrl(ResolveOriginalUrlDTO dto);

}
