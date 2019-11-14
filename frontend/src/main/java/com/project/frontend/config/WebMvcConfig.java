package com.project.frontend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Collections;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {


//    @Bean
//    public FilterRegistrationBean loginRegisterFilter(){
//        FilterRegistrationBean loginRegisterFilter = new FilterRegistrationBean();
//        loginRegisterFilter.setFilter(getApplicationContext().getBean(LoginFilter.class));
//        loginRegisterFilter.addUrlPatterns("/*");
//        return loginRegisterFilter;
//    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/index","/templates/index.html");
//        registry.addRedirectViewController("/","/templates/login.html");
//        super.addViewControllers(registry);
//    }




}
