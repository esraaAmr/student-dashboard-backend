package com.example.rest.util.singleton;


public class DbConnectionThreadSafe {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";

    private static DbConnectionThreadSafe DB_CONNECTION_LAZY;

    private DbConnectionThreadSafe() {
    }

    public static synchronized DbConnectionThreadSafe getInstance() {
        if (DB_CONNECTION_LAZY == null) {
            DB_CONNECTION_LAZY =new DbConnectionThreadSafe();
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
