package com.example;

public class UserClass {
    private String username;
    private String email;

    public UserClass(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserClass{username='" + username + "', email='" + email + "'}";
    }
}