package com.example.onlinecoffeeshop.repository;

import com.example.onlinecoffeeshop.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {

    List<Cart> findByUserId(Long userId);

    Boolean existsByUserIdAndProductNameAndSizeAndSugar(Long userId, String productName, int size, int sugar);
}
