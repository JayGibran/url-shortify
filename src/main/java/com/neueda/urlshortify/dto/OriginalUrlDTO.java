package com.neueda.urlshortify.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
public class OriginalUrlDTO  implements Serializable{

    public String originalUrl;

}
