package com.example.rest.util.singleton;

import org.springframework.stereotype.Component;

//@Component
public class DbConnectionBean {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";


    public DbConnectionBean() {
        System.out.println("DbConnectionBean()");
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
