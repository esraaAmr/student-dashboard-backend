package com.example.rest.model.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@ToString(of = {"firstName","lastName"})
public class Student {
    private final String firstName;
    private final String lastName;

    private String email;
    private String phone;
    private String gender;
    private int age;
}
