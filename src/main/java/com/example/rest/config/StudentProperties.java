package com.example.rest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student.egyptian")
@Data
public class StudentProperties {
    private String username;
    private String age;
    private String email;
    private Address address;


    @Data
    public static class Address{
        private String Continent;
        private String Country;
        private String government;
        private String city;
        private String street;
        private int buildingNumber;
        private int floor;
        private int apartmentNumber;

    }
}
