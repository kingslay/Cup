package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    public static final String Resource = System.getProperty("user.dir") + "/resource";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/uploads/**").addResourceLocations("file:" + Resource + "/images/uploads/").setCachePeriod(3600*24*365);
    }
}