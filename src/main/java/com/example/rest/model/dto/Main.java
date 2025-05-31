package com.example.rest.model.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Student student = new Student("dd","esraa");
        Employee employee = new Employee("dd","esraa");
        log.error("esraaaa");
        System.out.println(employee);
    }
}
