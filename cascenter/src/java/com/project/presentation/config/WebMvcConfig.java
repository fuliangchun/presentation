package com.project.presentation.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

//
//    @Bean
//    public FilterRegistrationBean loginFilter(){
//        FilterRegistrationBean loginFilter = new FilterRegistrationBean();
////        loginFilter.setFilter();
//    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/index","/templates/index.html");
//        registry.addRedirectViewController("/","/templates/login.html");
//        super.addViewControllers(registry);
//    }



}
