package com.example.caponehack.Firebase;

public class User {
    private String name;
    private String userType;
    private String userId;

    public User(String name, String userType, String userId){
        this.name = name;
        this.userType = userType;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserId() {
        return userId;
    }
}
