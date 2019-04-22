package com.talendorse.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class StartWebApplication  extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(StartWebApplication.class);
//    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartWebApplication.class, args);
    }
}
