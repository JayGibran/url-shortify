package com.neueda.urlshortify.model;

import lombok.*;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    public Long hits = 0l;

    private Browser browser = new Browser();

    private OS os = new OS();

    public void incrementHits(){
        hits++;
    }

}
