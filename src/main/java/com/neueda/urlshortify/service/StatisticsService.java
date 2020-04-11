package com.neueda.urlshortify.service;

import com.neueda.urlshortify.dto.StatisticsDTO;

public interface StatisticsService {

    StatisticsDTO getStatistics(String key);
}
