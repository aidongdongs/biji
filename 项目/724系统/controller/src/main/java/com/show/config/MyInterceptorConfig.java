package com.show.config;

import com.show.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/calendar/**").addResourceLocations("/statics/calendar/");
        registry.addResourceHandler("/statics/css/**").addResourceLocations("/statics/css/");
        registry.addResourceHandler("/statics/images/**").addResourceLocations("/statics/images/");
        registry.addResourceHandler("/statics/js/**").addResourceLocations("/statics/js/");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/sys/user/login")
                .excludePathPatterns("/route/toLogin")
        ;


    }
}
