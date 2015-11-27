package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.io.File;
import java.io.IOException;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    public static final String getResourcePath(){
        String filePath = System.getProperty("user.dir")+"/resource";
        File file = new File(filePath);
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/";
    }

    // equivalents for <mvc:resources/> tags
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/uploads/**").addResourceLocations("file:"+WebMvcConfig.getResourcePath()+"/image/uploads/");
    }
}