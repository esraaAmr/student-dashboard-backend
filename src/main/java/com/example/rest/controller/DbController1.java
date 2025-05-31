package com.example.rest.controller;

import com.example.rest.util.singleton.DbConnectionBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("db1")
public class DbController1 {

    private DbConnectionBean dbConnection;

    public DbController1() {
        System.out.println("In Controller1");
    }

    @Autowired(required = false)
    public DbController1(DbConnectionBean dbConnection) {
        log.info("In Controller1");
        this.dbConnection = dbConnection;
    }


    @PostMapping("connect")
    public void connectApi (){
        dbConnection.connect();
    }

    @PostMapping("changePassword/{password}")
    public void changePasswordApi(@PathVariable String password){
        dbConnection.setPassword(password);
    }
}
