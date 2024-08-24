package com.teodev77.pizzeria.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String invoiceDetails;
    private Double totalPrice;

    public void generateInvoiceDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(order.getId()).append("\n");
        details.append("Customer Name: ").append(order.getCustomerName()).append("\n");
        details.append("Pizzas: \n");
        order.getPizzas().forEach(pizza -> {
            details.append(" - ").append(pizza.getName()).append(": $").append(pizza.getBasePrice()).append("\n");
        });
        if (order.getAdditionalIngredientsCount() > 0) {
            details.append("Additional Ingredients: ").append(order.getAdditionalIngredientsCount())
                    .append(" ($").append(order.getAdditionalIngredientsCount() * 5.0).append(")\n");
        }
        details.append("Total Price: $").append(order.getTotalPrice()).append("\n");
        this.invoiceDetails = details.toString();
        this.totalPrice = order.getTotalPrice();
    }
}
