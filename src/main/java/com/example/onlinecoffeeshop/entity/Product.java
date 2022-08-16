package com.example.onlinecoffeeshop.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name="size", nullable = false)
    private int size;
    @Column(name="sugar")
    private int sugar;
    @Column(name = "is_added_to_cart")
    private boolean isAddedToCart;
    @Column(name = "bought_items_count")
    private int boughtItemsCount;
}
