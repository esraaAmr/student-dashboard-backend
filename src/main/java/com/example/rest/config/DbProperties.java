package com.example.rest.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@ConfigurationProperties(prefix = "db")
@Data
public class DbProperties {
    private String url;
    private String username;
    private String password;

    public DbProperties() {
        log.info("In DbProperties constructor");
    }
}
