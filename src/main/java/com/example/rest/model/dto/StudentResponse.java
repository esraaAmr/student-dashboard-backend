package com.example.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String name;
    private String email;
    private int age;
    private String address;

}
