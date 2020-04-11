package com.neueda.urlshortify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OS {

    private Long windows=0L;

    private Long macOs=0L;

    private Long linux=0L;

    private Long android=0L;

    private Long ios=0L;

    private Long others=0L;


    public void incrementWindows(){
        windows++;
    }

    public void incrementMacOs(){
        macOs++;
    }

    public void incrementLinux(){
        linux++;
    }

    public void incrementAndroid(){
        android++;
    }

    public void incrementIos(){
        ios++;
    }

    public void incrementOthers(){
        others++;
    }
}
