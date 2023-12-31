package ru.practicum.ShoppingCart.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;


// Покупатель
@Data
@Entity
@Table(name = "customer")
@ApiModel(description = "Покупатель")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String surname;
    private String address;
    private String email;
    private String phone;
}