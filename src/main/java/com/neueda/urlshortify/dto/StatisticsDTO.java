package com.neueda.urlshortify.dto;

import com.neueda.urlshortify.model.Browser;
import com.neueda.urlshortify.model.OS;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
public class StatisticsDTO implements Serializable {

    private Long hits;

    private Browser browser;

    private OS os;

    private LocalDateTime lastAccess;
}
