package com.neueda.urlshortify.helper;

import com.neueda.urlshortify.dto.ResolveOriginalUrlDTO;
import com.neueda.urlshortify.model.Statistics;
import org.springframework.stereotype.Component;

@Component
public class StatisticsHelper {

     public Statistics updateStats(ResolveOriginalUrlDTO dto, Statistics statistics) {
        statistics.incrementHits();
        switch (dto.getBrowser().toLowerCase()) {
            case "internet explorer":
                statistics.getBrowser().incrementInternet();
                break;
            case "safari":
                statistics.getBrowser().incrementSafari();
                break;
            case "chrome":
                statistics.getBrowser().incrementChrome();
                break;
            case "firefox":
                statistics.getBrowser().incrementFirefox();
                break;
            case "brave":
                statistics.getBrowser().incrementBrave();
                break;
            case "opera":
                statistics.getBrowser().incrementOpera();
                break;

            default:
                statistics.getBrowser().incrementOthers();
                break;
        }

        switch (dto.getOs().toLowerCase()) {
            case "windows":
                statistics.getOs().incrementWindows();
                break;
            case "mac_os":
                statistics.getOs().incrementMacOs();
                break;
            case "linux":
                statistics.getOs().incrementLinux();
                break;
            case "android":
                statistics.getOs().incrementAndroid();
                break;
            case "iphone":
                statistics.getOs().incrementIos();
                break;

            default:
                statistics.getOs().incrementOthers();
                break;
        }
        return statistics;
    }
}
