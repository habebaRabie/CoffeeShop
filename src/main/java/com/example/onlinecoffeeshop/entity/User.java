package com.example.onlinecoffeeshop.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "users")
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name",nullable = false, length = 100, unique = true)
    private String userName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Cart> userCart;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String userName, String email, String password, List<Cart> userCart) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userCart = userCart;
    }


}