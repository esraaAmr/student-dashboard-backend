package com.example.rest.util.builder;

public class Main {
    public static void main(String[] args) {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("esrraaaa")
                .age(22)
                .phone("123456789")
                .gender("Male").build();

        System.out.println(student);
    }
}
