package com.example.onlinecoffeeshop.repository;

import com.example.onlinecoffeeshop.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findByName(String name);

    void deleteByName(String name);
}
