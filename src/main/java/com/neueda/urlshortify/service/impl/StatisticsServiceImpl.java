package com.neueda.urlshortify.service.impl;

import com.neueda.urlshortify.dto.StatisticsDTO;
import com.neueda.urlshortify.model.Url;
import com.neueda.urlshortify.repository.UrlRepository;
import com.neueda.urlshortify.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public StatisticsDTO getStatistics(String key) {
        Optional<Url> optionalUrl  = urlRepository.findByKey(key);
        if(!optionalUrl.isPresent()) return null;
        Url url = optionalUrl.get();
        StatisticsDTO dto = StatisticsDTO
                .builder()
                .hits(url.getStatistics().getHits())
                .browser(url.getStatistics().getBrowser())
                .os(url.getStatistics().getOs())
                .lastAccess(url.getLastAccessDate())
                .build();

        return dto;
    }

}
