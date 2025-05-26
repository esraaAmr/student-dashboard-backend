package com.example.rest.controller;

import com.example.rest.util.singleton.DbConnectionBean;
import com.example.rest.util.singleton.DbConnectionDoubleCheckedLocking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db2")
public class DbController2 {

    private DbConnectionBean dbConnection;

    public DbController2() {
        System.out.println("In Controller2");
    }

    //Constructor Injection
    @PostMapping("getPassword")
    public String getPasswordApi(){
        return dbConnection.getPassword();
    }

    @Autowired(required = false)
    public void setDbConnectionBean(DbConnectionBean dbConnection){
        System.out.println("setDbConnectionBean");
        this.dbConnection=dbConnection;
    }
}
