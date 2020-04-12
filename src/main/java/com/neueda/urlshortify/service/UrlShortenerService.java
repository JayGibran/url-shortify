package com.neueda.urlshortify.service;

import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.dto.KeyUrlDTO;
import com.neueda.urlshortify.dto.UrlResponseDTO;

public interface UrlShortenerService {

    public KeyUrlDTO shortenUrl(String originalUrl);

    public UrlResponseDTO getOriginalUrl(ResolveOriginalUrlDTO dto);

}
