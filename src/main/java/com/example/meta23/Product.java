package com.example.meta23;

public class Product {
    private Long id;
    private String name;
    private double price;   // precio de venta
    private double cost;    // costo de producción
    private int stock;

    public Product(Long id, String name, double price, double cost, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.stock = stock;
    }

    public void updateStock(int qty) {
        stock += qty;
    }

    public String getInfo() {
        return name + " | Precio: $" + price + " | Stock: " + stock;
    }

    public double getProfitPerUnit() {
        return price - cost;
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getCost() { return cost; }
    public int getStock() { return stock; }
}
