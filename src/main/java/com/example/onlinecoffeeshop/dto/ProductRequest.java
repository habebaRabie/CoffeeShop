package com.example.onlinecoffeeshop.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {
    private Long id;
    private String name;
    private Double price;
    private String image;
    private int size;
    private int sugar;
    private boolean isAddedToCart;
    private int boughtItemsCount;
}