package com.neueda.urlshortify.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@Document(collection = "url")
public class Url {

    @Id
    private String id;

    @Indexed
    private String key;

    private LocalDateTime createdDate;

    private LocalDateTime lastAccessDate;

    @Indexed
    private String originalUrl;

    private Statistics statistics;

}
