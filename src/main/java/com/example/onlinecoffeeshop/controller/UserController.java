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
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtility jwtTokenUtil;

    @PostMapping(value = "/add")
    public String createNewUser(@RequestBody UserDto user) throws Exception {
        userService.createNewUser(user);
        return "200 OK";
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
//        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
//        String token = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(,token));

        CustomUser customUser = (CustomUser) userService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(customUser);

        return ResponseEntity.ok(new AuthenticationResponse(customUser.getUsername(), customUser.getEmail(), token));
    }

}