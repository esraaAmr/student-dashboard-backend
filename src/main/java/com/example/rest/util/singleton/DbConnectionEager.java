package com.example.rest.util.singleton;

public class DbConnectionEager {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";

    private static final DbConnectionEager DB_CONNECTION_EAGER =new DbConnectionEager();

    private DbConnectionEager() {
    }

    public static DbConnectionEager getInstance() {
        return DB_CONNECTION_EAGER;
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
