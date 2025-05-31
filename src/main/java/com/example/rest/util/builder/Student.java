package com.example.rest.util.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class Student {
    private  String firstName;
    private  String lastName;
    private String email;
    private String phone;
    private String gender;
    private int age;

//    public Student(StudentBuilder builder) {
//        this.firstName = builder.firstName;
//        this.lastName = builder.lastName;
//        this.email = builder.email;
//        this.phone = builder.phone;
//        this.gender = builder.gender;
//        this.age = builder.age;
//    }


//    public static StudentBuilder builder() {
//        return new StudentBuilder();
//    }
//    public static class StudentBuilder{
//        private  String firstName;
//        private  String lastName;
//        private String email;
//        private String phone;
//        private String gender;
//        private int age;

//        public StudentBuilder firstName(String firstName){
//            this.firstName=firstName;
//            return this;
//        }
//
//        public StudentBuilder lastName(String lastName){
//            this.lastName=lastName;
//            return this;
//        }
//
//        public StudentBuilder email(String email){
//            this.email=email;
//            return this;
//        }
//
//        public StudentBuilder phone(String phone){
//            this.phone=phone;
//            return this;
//        }
//
//        public StudentBuilder gender(String gender){
//            this.gender=gender;
//            return this;
//        }
//
//        public StudentBuilder age(int age){
//            this.age=age;
//            return this;
//        }
//
//        public Student build(){
//            return new Student(this);
//        }

    }

