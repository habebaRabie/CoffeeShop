package com.example.onlinecoffeeshop.service;

import com.example.onlinecoffeeshop.entity.Product;
import com.example.onlinecoffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product createNewProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String name) {
        productRepository.deleteByName(name);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
