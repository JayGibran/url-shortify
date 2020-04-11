package com.neueda.urlshortify.dto;

import com.neueda.urlshortify.model.Browser;
import com.neueda.urlshortify.model.OS;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class StatisticsDTO {

    public Long hits;

    public Browser browser;

    public OS os;

    public LocalDateTime lastAccess;
}
