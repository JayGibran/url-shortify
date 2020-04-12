package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.KeyUrlDTO;
import com.neueda.urlshortify.dto.StatisticsDTO;
import com.neueda.urlshortify.exception.KeyNotFoundException;
import com.neueda.urlshortify.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{key}")
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable String key) throws KeyNotFoundException{
        StatisticsDTO dto = statisticsService.getStatistics(key);
        if (dto == null) {
            throw new KeyNotFoundException(messageSource.getMessage(
                    "key.not.found", null, LocaleContextHolder.getLocale()));
        }
        return new ResponseEntity<StatisticsDTO>(dto, HttpStatus.OK);
    }
}
