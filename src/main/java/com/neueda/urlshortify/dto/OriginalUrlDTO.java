package com.neueda.urlshortify.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class OriginalUrlDTO implements Serializable {

    private String originalUrl;
}
