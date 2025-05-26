package com.example.rest.entity;

import java.util.Objects;

public class StudentEntity {
    private String id;
    private String name;
    private String email;
    private int age;
    private String address;
    private String password;
    private boolean isLoggedIn;

    public StudentEntity() {
    }

    public StudentEntity(String id, String name, String email, int age, String address, String password,boolean isLoggedIn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return getAge() == that.getAge() && isLoggedIn() == that.isLoggedIn() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getAge(), getAddress(), getPassword(), isLoggedIn());
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
