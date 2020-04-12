package com.neueda.urlshortify.service.impl;

import com.google.common.hash.Hashing;
import com.neueda.urlshortify.dto.OriginalUrlDTO;
import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.dto.KeyUrlDTO;
import com.neueda.urlshortify.dto.UrlResponseDTO;
import com.neueda.urlshortify.helper.StatisticsHelper;
import com.neueda.urlshortify.model.Statistics;
import com.neueda.urlshortify.model.Url;
import com.neueda.urlshortify.repository.UrlRepository;
import com.neueda.urlshortify.service.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private StatisticsHelper statisticsHelper;

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public KeyUrlDTO shortenUrl(String originalUrl) {
        Url savedUrl = urlRepository.findByOriginalUrl(originalUrl).orElseGet(() -> saveNewUrl(originalUrl));
        KeyUrlDTO dto = KeyUrlDTO.builder().key(savedUrl.getKey()).build();
        return dto;
    }

    private Url saveNewUrl (String originalUrl){
        String key = Hashing.murmur3_32().hashString(originalUrl, Charset.defaultCharset()).toString();
        Url newUrlSaved = Url.builder()
                .originalUrl(originalUrl)
                .key(key)
                .statistics(new Statistics())
                .createdDate(LocalDateTime.now())
                .build();
        newUrlSaved = urlRepository.save(newUrlSaved);
        return newUrlSaved;
    }

    @Override
    public UrlResponseDTO getOriginalUrl(ResolveOriginalUrlDTO dto) {
        Optional<Url> optionalUrl = urlRepository.findByKey(dto.getKey());
        if(!optionalUrl.isPresent()) return null;

        Url url = optionalUrl.get();
        url.setStatistics(statisticsHelper.updateStats(dto, url.getStatistics()));
        url.setLastAccessDate(LocalDateTime.now());
        urlRepository.save(url);
        UrlResponseDTO responseDTO = UrlResponseDTO.builder().originalUrl(url.getOriginalUrl()).build();
        return responseDTO;
    }



}
