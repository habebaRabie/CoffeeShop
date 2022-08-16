package com.example.onlinecoffeeshop.controller;

import com.example.onlinecoffeeshop.entity.Cart;
import com.example.onlinecoffeeshop.entity.User;
import com.example.onlinecoffeeshop.security.JwtUtility;
import com.example.onlinecoffeeshop.service.CartService;
import com.example.onlinecoffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtility jwtTokenUtil;


    @GetMapping("/getCart")
    public ResponseEntity<?> getCart(@RequestBody String token) {
        //check if token is valid
        if (jwtTokenUtil.validateToken(token)) {
            System.out.println("Token is valid");
            User user = userService.findByEmail(jwtTokenUtil.extractUsername(token));
            return ResponseEntity.ok(cartService.findByUserId(user.getId()));
        }else{
            System.out.println("Token is not valid");
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
