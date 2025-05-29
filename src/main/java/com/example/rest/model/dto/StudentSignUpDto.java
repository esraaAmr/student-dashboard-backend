package com.example.rest.model.dto;

import lombok.Data;


@Data
public class StudentSignUpDto {

    private String name;
    private String email;
    private int age;
    private String address;
    private String password;

    public StudentSignUpDto(String name,String email, int age,  String address, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.password = password;
    }


}
