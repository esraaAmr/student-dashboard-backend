package com.example.rest.model.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class UpdateStudentDto {

    private String name;
    private String email;
    private int age;
    private String address;
    private String password;

    public UpdateStudentDto(String name,String email, int age,  String address, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.password = password;
    }

}
