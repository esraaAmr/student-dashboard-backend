package com.example.rest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "db")
@Data
public class DbProperties {
    private String url;
    private String username;
    private String password;

}
