package com.example.onlinecoffeeshop.dto;

public class AuthenticationResponse {
    private String token;
    private String userName;
    private String email;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public AuthenticationResponse(String userName, String email, String token) {
        this.userName = userName;
        this.email = email;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}