package com.example.onlinecoffeeshop.service;

import com.example.onlinecoffeeshop.dto.AuthenticationRequest;
import com.example.onlinecoffeeshop.dto.AuthenticationResponse;
import com.example.onlinecoffeeshop.dto.CustomUser;
import com.example.onlinecoffeeshop.dto.UserDto;
import com.example.onlinecoffeeshop.entity.Cart;
import com.example.onlinecoffeeshop.entity.User;
import com.example.onlinecoffeeshop.repository.UserRepository;
import com.example.onlinecoffeeshop.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);


        if (user == null)
            throw new UsernameNotFoundException("Email: " + email + " Not found");

        return new CustomUser(user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                mapToGrantedAuthorities(),
                user.getEmail());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        return grantedAuthoritiesList;

    }

    //Registration
    public ResponseEntity<?> createNewUser(UserDto userDto) throws Exception {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
//        List<Cart> cartList = new ArrayList<>();
        User newUser;
        try {
//            newUser = new User(null, userDto.getUserName(), userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword()), cartList);
            newUser = new User(null, userDto.getUserName(), userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword()));
            return ResponseEntity.ok(userRepository.save(newUser));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> login(AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        CustomUser customUser = (CustomUser) loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(customUser);

        return ResponseEntity.ok(new AuthenticationResponse(customUser.getUsername(), customUser.getEmail(), token));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}