package com.neueda.urlshortify.controller;

import com.neueda.urlshortify.dto.StatisticsDTO;
import com.neueda.urlshortify.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{key}")
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable String key) {
        StatisticsDTO dto = statisticsService.getStatistics(key);
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(dto);
    }


}
