package com.example.onlinecoffeeshop.service;

import com.example.onlinecoffeeshop.entity.Cart;
import com.example.onlinecoffeeshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }


    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    public boolean existsByUserIdAndProductNameAndSizeAndSugar(Long userId, String productName, int size, int sugar) {
        return cartRepository.existsByUserIdAndProductNameAndSizeAndSugar(userId, productName, size, sugar);
    }
}
