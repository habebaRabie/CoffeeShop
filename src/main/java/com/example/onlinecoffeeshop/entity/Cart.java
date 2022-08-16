package com.example.onlinecoffeeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Cart.class)
@Entity(name = "cart")
public class Cart implements Serializable {
    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Id
    @Column(name="product_name")
    private String productName;

    @Id
    @Column(name="size")
    private int size;

    @Id
    @Column(name="sugar")
    private int sugar;

    @Column(name="count")
    private int count;

    @Column(name="price")
    private double price;
}
