package com.neueda.urlshortify.helper;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestHelper {

    private static final UrlValidator URL_VALIDATOR = new UrlValidator();

    public String getOperatingSystemType(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent.getOperatingSystem().getGroup().getName();
    }
    public  String getBrowserType(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent.getBrowser().getGroup().getName();
    }

    public  boolean isValid(String url) {
        return URL_VALIDATOR.isValid(url);
    }

    public String urlNormalization(String url) {
        if (!url.toLowerCase().startsWith("https://") && !url.toLowerCase().startsWith("http://"))
            url = "http://".concat(url);
        if (url.indexOf("www") < 0)
            url = url.replace("://", "://www.");

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);

        return url;
    }

}
