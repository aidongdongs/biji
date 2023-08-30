package com.show.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/statics/calendar/**").addResourceLocations("/statics/calendar/");
//        registry.addResourceHandler("/statics/css/**").addResourceLocations("/statics/css/");
//        registry.addResourceHandler("/statics/images/**").addResourceLocations("/statics/images/");
//        registry.addResourceHandler("/statics/js/**").addResourceLocations("/statics/js/");
//    }


}
