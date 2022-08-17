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
    public ResponseEntity<?> getCart(@RequestParam("email") String email) {
        System.out.println(email);
        User user = userService.findByEmail(email);
        if(user==null){
            return ResponseEntity.badRequest().body("User Not Found");
        }
        return ResponseEntity.ok(cartService.findByUserId(user.getId()));

    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody List<Cart> cart, @RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        if(user==null){
            return ResponseEntity.badRequest().body("User Not Found");
        }
        for (Cart cart1 : cart) {
            cart1.setUser(user);
            cartService.save(cart1);
        }
        return ResponseEntity.ok(cartService.findByUserId(user.getId()));
    }
}
