package com.example.rest.config;

import com.example.rest.util.singleton.DbConnectionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig constructor");
    }

    @Bean
    public DbConnectionBean dbConnectionBean() {
        return new DbConnectionBean();
    }
}
