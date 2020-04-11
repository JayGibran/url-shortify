package com.neueda.urlshortify.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
public class Statistics {

    private Long id;

    private String browser;

    private String os;

    private LocalDateTime dateAccess;

}
