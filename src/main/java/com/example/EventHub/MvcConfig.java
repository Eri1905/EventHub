package com.example.EventHub;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/event/add").setViewName("event");
        registry.addViewController("/event/update").setViewName("update");
        registry.addViewController("/event/delete").setViewName("delete");
        registry.addViewController("/organisation/add").setViewName("organisation");
        registry.addViewController("/organisation/update").setViewName("updateOrg");
        registry.addViewController("/organisation/delete").setViewName("deleteOrg");

    }

}
