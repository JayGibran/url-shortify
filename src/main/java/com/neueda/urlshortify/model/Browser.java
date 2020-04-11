package com.neueda.urlshortify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Browser {

    private Long firefox = 0l;

    private Long chrome = 0l;

    private Long internetExplore = 0l;

    private Long opera = 0l;

    private Long safari = 0l;

    private Long brave = 0l;

    private Long others = 0l;

    public void incrementInternet(){
        internetExplore++;
    }

    public void incrementChrome(){
        chrome++;
    }

    public void incrementFirefox(){
        firefox++;
    }

    public void incrementOpera(){
        opera++;
    }

    public void incrementSafari(){
        safari++;
    }

    public void incrementBrave(){
        brave++;
    }

    public void incrementOthers(){
        others++;
    }
}
