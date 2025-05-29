package com.example.rest.controller;

import com.example.rest.config.StudentProperties;
import com.example.rest.util.singleton.DbConnectionBean;
import com.example.rest.util.singleton.DbConnectionDoubleCheckedLocking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db2")
public class DbController2 {

    private final DbConnectionBean dbConnection;

    private final StudentProperties studentProperties;

    @Autowired
    public DbController2(DbConnectionBean dbConnection, StudentProperties studentProperties) {
        this.dbConnection = dbConnection;
        this.studentProperties = studentProperties;
    }

    //Constructor Injection
    @PostMapping("getPassword")
    public String getPasswordApi(){
        return dbConnection.getPassword()+ "/" +studentProperties.getUsername();
    }

}
