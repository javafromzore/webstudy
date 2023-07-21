package com.hhb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展SpringMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //页面跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");

        registry.addViewController("/userIndex.html").setViewName("userIndex");
        registry.addViewController("/managerIndex.html").setViewName("managerIndex");
        registry.addViewController("/organizationIndex.html").setViewName("organizationIndex");
    }
}
