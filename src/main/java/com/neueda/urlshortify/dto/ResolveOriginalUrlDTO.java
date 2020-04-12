package com.neueda.urlshortify.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResolveOriginalUrlDTO {

    private String key;

    private String browser;

    private String os;

}
