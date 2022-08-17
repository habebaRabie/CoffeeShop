package com.example.onlinecoffeeshop.repository;

import com.example.onlinecoffeeshop.entity.Cart;
import com.example.onlinecoffeeshop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByEmail(String email);
    User findByPassword(String password);
}
