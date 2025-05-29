package com.example.rest.config;

import com.example.rest.util.singleton.DbConnectionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({DbProperties.class,StudentProperties.class})
public class DbConfig {

    private final DbProperties dbProperties;

    @Autowired
    public DbConfig(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
    }


    @Bean
    public DbConnectionBean dbConnectionBean() {
        return new DbConnectionBean(dbProperties.getUrl(), dbProperties.getUsername(), dbProperties.getPassword());
    }
}
