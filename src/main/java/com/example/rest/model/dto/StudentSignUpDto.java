package com.example.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSignUpDto {

    private String name;
    private String email;
    private int age;
    private String address;

    @ToString.Exclude
    private String password;
}
