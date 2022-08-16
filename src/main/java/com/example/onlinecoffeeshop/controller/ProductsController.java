package com.example.onlinecoffeeshop.controller;

import com.example.onlinecoffeeshop.entity.Product;
import com.example.onlinecoffeeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(params = "name")
    public Product getProductByName(@RequestParam("name") String name) {
        return productService.getProductByName(name);
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        if(product==null){
            return ResponseEntity.badRequest().body("Product is null");
        }
        if(productService.getProductByName(product.getName()) != null) {
            return ResponseEntity.badRequest().body("Product with name " + product.getName() + " already exists");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name is required");
        } else if (product.getPrice() == null || product.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Product price is required");
        } else {
            return ResponseEntity.ok(productService.createNewProduct(product));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        if(product==null){
            return ResponseEntity.badRequest().body("Product is null");
        }
        if(productService.getProductByName(product.getName()) == null) {
            return ResponseEntity.badRequest().body("Product with name " + product.getName() + " does not exist");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name is required");
        } else if (product.getPrice() == null || product.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Product price is required");
        } else {
            return ResponseEntity.ok(productService.updateProduct(product));
        }
    }

    @Transactional
    @DeleteMapping(params = "name")
    public ResponseEntity<?> deleteProduct(@RequestParam("name") String name) {
        if(productService.getProductByName(name) == null) {
            return ResponseEntity.badRequest().body("Product with name " + name + " does not exist");
        }
        productService.deleteProduct(name);
        return ResponseEntity.ok().build();
    }

}
