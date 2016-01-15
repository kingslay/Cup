package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.AppCacheManifestTransformer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.Date;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Value("${resources.projectroot:}")
    private String projectRoot;

    @Value("${app.version:}")
    private String appVersion;

    public static final String Resource = System.getProperty("user.dir") + "/resource";

    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/uploads/**").addResourceLocations("file:" + Resource + "/images/uploads/").setCachePeriod(3600 * 24 * 365);
//        String version = "1.1.0";
//        VersionResourceResolver versionResolver = new VersionResourceResolver()
//                .addFixedVersionStrategy(version, "/**/*.js")
//                .addContentVersionStrategy("/**");
//
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:static/")
//                .addResourceLocations("classpath:public/")
//                .setCachePeriod(3600 * 24 * 10)
//                .resourceChain(true)
//                .addResolver(versionResolver)
//                .addTransformer(new AppCacheManifestTransformer());

    }
}