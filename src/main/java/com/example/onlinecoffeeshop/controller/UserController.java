package com.example.onlinecoffeeshop.controller;

import com.example.onlinecoffeeshop.dto.AuthenticationRequest;
import com.example.onlinecoffeeshop.dto.AuthenticationResponse;
import com.example.onlinecoffeeshop.dto.CustomUser;
import com.example.onlinecoffeeshop.dto.UserDto;
import com.example.onlinecoffeeshop.entity.User;
import com.example.onlinecoffeeshop.service.UserService;
import com.example.onlinecoffeeshop.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtility jwtTokenUtil;


    @GetMapping(value = "/getallusers")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(value = "/add")
    public String createNewUser(@RequestBody UserDto user) throws Exception {
        userService.createNewUser(user);
        return "200 OK";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return userService.login(authenticationRequest);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

//    @GetMapping(value = "/updateProfile")
//    public void updateProfile( @RequestHeader String Authorization){
////        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
////                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
////        System.out.println(usernamePasswordAuthenticationToken.getPrincipal().toString());
//
////            User user = userService.findByEmail(jwtTokenUtil.extractUsername(token));
////            UserDetails userDetails = userService.loadUserByUsername()
////        System.out.println(Authorization);
//        System.out.println("Error ====  "+ jwtTokenUtil.extractSubject(Authorization));
//
//
//    }

}