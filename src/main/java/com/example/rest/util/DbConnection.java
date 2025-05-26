package com.example.rest.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class DbConnection {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";

    public DbConnection() {
    }

    public void connect(){
        System.out.println("Connecting to database...");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
