package com.alok.home.commons.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Map;

@ConfigurationProperties(prefix = "auth.token.issuer")
@ConfigurationPropertiesScan
public class TokenIssuerProperties {
    private Map<String, String> urls;

    public TokenIssuerProperties(Map<String, String> urls) {
        this.urls = urls;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }
}

