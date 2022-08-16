package com.example.onlinecoffeeshop.repository;

import com.example.onlinecoffeeshop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByEmail(String email);
}
