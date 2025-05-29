package com.example.rest.controller;

import com.example.rest.config.DbProperties;
import com.example.rest.config.StudentProperties;
import com.example.rest.entity.AddressFormatter;
import com.example.rest.model.dto.StudentSignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("welcome")
public class WelcomeController {

    private final StudentProperties studentProperties;

    @Autowired
    public WelcomeController(StudentProperties studentProperties) {
        this.studentProperties = studentProperties;
    }


    @PostMapping
    public String welcomeApi() {
        StudentProperties.Address address = studentProperties.getAddress();

        return "Name: " + studentProperties.getUsername()
                + ", Age: " + studentProperties.getAge()
                + ", Email: " + studentProperties.getEmail()
                + ", Address: " + AddressFormatter.formatAddress(address);
    }

}
