package com.example.rest.util.singleton;


public class DbConnectionLazy {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";

    private static DbConnectionLazy DB_CONNECTION_LAZY;

    private DbConnectionLazy() {
    }

    public static DbConnectionLazy getInstance() {
        if (DB_CONNECTION_LAZY == null) {
            DB_CONNECTION_LAZY =new DbConnectionLazy();
        }
        return DB_CONNECTION_LAZY;
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
