package com.example.onlinecoffeeshop.service;

import com.example.onlinecoffeeshop.dto.CustomUser;
import com.example.onlinecoffeeshop.dto.UserDto;
import com.example.onlinecoffeeshop.entity.Cart;
import com.example.onlinecoffeeshop.entity.User;
import com.example.onlinecoffeeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Cart> cartList = new ArrayList<>();
        User newUser;
        try {
            newUser = new User(null, userDto.getUserName(), userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword()), cartList);
            return ResponseEntity.ok(userRepository.save(newUser));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}