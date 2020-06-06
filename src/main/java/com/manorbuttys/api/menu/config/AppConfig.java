package com.manorbuttys.api.menu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final WebEndpointProperties webEndpointProperties;

    @Autowired
    public AppConfig(WebEndpointProperties webEndpointProperties) {
        this.webEndpointProperties = webEndpointProperties;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/health").setViewName("forward:" + webEndpointProperties.getBasePath() + "/health");
        registry.addViewController("/info").setViewName("forward:" + webEndpointProperties.getBasePath() + "/info");
    }
}