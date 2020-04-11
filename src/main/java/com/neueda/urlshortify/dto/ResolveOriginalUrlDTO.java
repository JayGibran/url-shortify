package com.neueda.urlshortify.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResolveOriginalUrlDTO {

    private String shortUrl;

    private String browser;

    private String os;

    @Override
    public String toString() {
        return "ResolveOriginalUrlDTO{" +
                "shortUrl='" + shortUrl + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
