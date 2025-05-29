package com.example.rest.util.singleton;

import org.springframework.stereotype.Component;

//@Component
public class DbConnectionBean {
    private String url;
    private String username;
    private String password;

    public DbConnectionBean(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

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
