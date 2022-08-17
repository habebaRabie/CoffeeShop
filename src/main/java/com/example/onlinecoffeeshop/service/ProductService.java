package com.example.onlinecoffeeshop.service;

import com.example.onlinecoffeeshop.dto.ProductRequest;
import com.example.onlinecoffeeshop.dto.ProductResponse;
import com.example.onlinecoffeeshop.entity.Product;
import com.example.onlinecoffeeshop.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        List<ProductResponse> productsResponseList = null;

        productsResponseList = new ArrayList<>();
        ProductResponse productResponse;

        for(Product product: products){
            productResponse = new ProductResponse();
            BeanUtils.copyProperties(product, productResponse);
            productsResponseList.add(productResponse);
        }
        return productsResponseList;
    }

    public ResponseEntity<?> getProductByName(String name) {
        if(productRepository.findByName(name) != null){
            return ResponseEntity.ok(productRepository.findByName(name));
        }
        return ResponseEntity.badRequest().body("Product with name " + name + " does not exist");
    }

    public ResponseEntity<?> createNewProduct(ProductRequest productRequest) {
        if(productRequest==null){
            return ResponseEntity.badRequest().body("Product is null");
        }
        if(getProductByName(productRequest.getName()) != null) {
            return ResponseEntity.badRequest().body("Product with name " + productRequest.getName() + " already exists");
        }
        if(productRequest.getName() == null || productRequest.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name is required");
        } else if (productRequest.getPrice() == null || productRequest.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Product price is required");
        } else {
            Product newProduct = new Product(productRequest.getName(),
                    productRequest.getPrice(),
                    productRequest.getImage(),
                    productRequest.getSize(),
                    productRequest.getSugar(),
                    productRequest.isAddedToCart(),
                    productRequest.getBoughtItemsCount());
            return ResponseEntity.ok(productRepository.save(newProduct));
        }
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product with id "+ id + " has been deleted successfully");
        }else{
            return ResponseEntity.badRequest().body("Product with id " + id + " does not exist");
        }
    }

    public ResponseEntity<?> updateProduct(ProductRequest productRequest) {
        if(productRequest==null){
            return ResponseEntity.badRequest().body("Product is null");
        }
        if(getProductByName(productRequest.getName()) == null) {
            return ResponseEntity.badRequest().body("Product with name " + productRequest.getName() + " does not exist");
        }
        if (!productRepository.existsByName(productRequest.getName()) || productRequest.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name is required");
        } else if (productRequest.getPrice() == null || productRequest.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Product price is required");
        } else {
            Product currentProduct = productRepository.findByName(productRequest.getName());
            currentProduct.setPrice(productRequest.getPrice());
            currentProduct.setImage(productRequest.getImage());
            currentProduct.setSize(productRequest.getSize());
            currentProduct.setSugar(productRequest.getSugar());
            currentProduct.setAddedToCart(productRequest.isAddedToCart());
            currentProduct.setBoughtItemsCount(productRequest.getBoughtItemsCount());

            return ResponseEntity.ok(productRepository.save(currentProduct));
        }
    }
}
