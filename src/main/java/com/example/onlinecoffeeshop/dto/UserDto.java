package com.example.onlinecoffeeshop.dto;


import com.example.onlinecoffeeshop.entity.Cart;

import java.util.List;

public class UserDto {
    private String userName;
    private String email;
    private String password;
//    private List<Cart> cart;

//    public List<Cart> getCart() {
//        return cart;
//    }
//    public void setCart(List<Cart> cart) {
//        this.cart = cart;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
