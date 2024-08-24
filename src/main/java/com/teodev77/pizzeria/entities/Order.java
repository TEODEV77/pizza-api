package com.teodev77.pizzeria.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private Double totalPrice;

    @ManyToMany
    @JoinTable(
            name = "order_pizzas",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzas;

    private int additionalIngredientsCount;

    public void calculateTotalPrice() {
        double pizzasPrice = pizzas.stream()
                .mapToDouble(Pizza::getBasePrice)
                .sum();
        double additionalIngredientsPrice = additionalIngredientsCount * 5.0;
        this.totalPrice = pizzasPrice + additionalIngredientsPrice;
    }
}
