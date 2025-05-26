package com.example.rest.util.singleton;

public class DbConnectionDoubleCheckedLocking {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";

    private static DbConnectionDoubleCheckedLocking DB_CONNECTION_LAZY;

    private DbConnectionDoubleCheckedLocking() {
    }

    public static  DbConnectionDoubleCheckedLocking getInstance() {
        if (DB_CONNECTION_LAZY == null) {
            synchronized (DbConnectionDoubleCheckedLocking.class) {
                DB_CONNECTION_LAZY = new DbConnectionDoubleCheckedLocking();
            }
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
