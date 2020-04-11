package com.neueda.urlshortify.service.impl;

import com.google.common.hash.Hashing;
import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.model.Statistics;
import com.neueda.urlshortify.model.Url;
import com.neueda.urlshortify.repository.UrlRepository;
import com.neueda.urlshortify.service.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlRepository urlRepository;

    private String NOT_FOUND = "NOT_FOUND";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String shortenUrl(String originalUrl) {
        logger.warn("LocalDate:"+ LocalDate.now());
        logger.warn("LocalDateTime:"+ LocalDateTime.now());
        Url savedUrl = urlRepository.findByOriginalUrl(originalUrl).orElseGet(() -> saveNewUrl(originalUrl));
        return savedUrl.getKey();
    }

    @Override
    public String getOriginalUrl(ResolveOriginalUrlDTO dto) {
        System.out.println(dto);
        Optional<Url> optionalUrl = urlRepository.findByKey(dto.getShortUrl());
        if(optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            Statistics stats = Statistics.builder().browser(dto.getBrowser()).os(dto.getOs()).dateAccess(LocalDateTime.now()).build();
            url.getStatistics().add(stats);
            urlRepository.save(url);
            return url.getOriginalUrl();
        };
        return NOT_FOUND;
    }

    private Url saveNewUrl (String originalUrl){
        String key = Hashing.murmur3_32().hashString(originalUrl, Charset.defaultCharset()).toString();
        Url newUrlSaved = Url.builder()
                .originalUrl(originalUrl)
                .key(key)
                .statistics(new ArrayList<>())
                .createdDate(LocalDateTime.now())
                .build();
        newUrlSaved = urlRepository.save(newUrlSaved);
        return newUrlSaved;
    }

}
