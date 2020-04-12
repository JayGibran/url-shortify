package com.neueda.urlshortify.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class KeyUrlDTO implements Serializable {

    private String key;

}
