package com.inventory.snackk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("Adding resource handlers");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/dist/browser/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("Adding view controllers");
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
    }
}
