package com.example.rest.util.singleton;

public class DbConnectionBillPugh {
    private String url="jdbc:mysql://localhost:3306/test";
    private String username="root";
    private String password="root";


    private DbConnectionBillPugh() {
    }

    //Inner Class
    private static class DbConnectionBillPughHelper{

        private static final DbConnectionBillPugh INSTANCE = new DbConnectionBillPugh();

        private DbConnectionBillPughHelper() {
        }
    }

    public static DbConnectionBillPugh getInstance() {
        return DbConnectionBillPughHelper.INSTANCE;
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
