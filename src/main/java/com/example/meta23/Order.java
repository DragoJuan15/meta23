package com.example.meta23;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Date date;
    private String status;
    private List<Product> products = new ArrayList<>();

    public Order(Long id) {
        this.id = id;
        this.date = new Date();
        this.status = "Creada";
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public double calculateProfit() {
        return products.stream().mapToDouble(Product::getProfitPerUnit).sum();
    }

    public void updateStatus(String newStatus) {
        status = newStatus;
    }

    public String getStatus() { return status; }
}
