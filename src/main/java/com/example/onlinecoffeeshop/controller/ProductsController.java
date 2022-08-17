package com.example.onlinecoffeeshop.controller;

import com.example.onlinecoffeeshop.dto.ProductRequest;
import com.example.onlinecoffeeshop.dto.ProductResponse;
import com.example.onlinecoffeeshop.entity.Product;
import com.example.onlinecoffeeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getAllProducts")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getByName", params = "name")
    public ResponseEntity<?> getProductByName(@RequestParam("name") String name) {
        return productService.getProductByName(name);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequest productRequest) {
        return productService.createNewProduct(productRequest);
    }

    @PutMapping(value = "/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productRequest);
    }

    @Transactional
    @DeleteMapping(value = "/deleteProduct", params = "id")
    public ResponseEntity<?> deleteProduct(@RequestParam("id") Long id) {
        return productService.deleteProduct(id);
    }

}
