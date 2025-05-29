package com.example.rest.model.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class StudentResponse {

    private String name;
    private String email;
    private int age;
    private String address;

    public StudentResponse(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

}
