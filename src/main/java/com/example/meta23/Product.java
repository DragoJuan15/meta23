package com.example.meta23;

public class Product {
    private Long id;
    private String name;
    private double price;   // precio de venta al cliente
    private double cost;    // costo de producción/compra
    private int stock;      // unidades disponibles en inventario

    public Product(Long id, String name, double price, double cost, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.stock = stock;
    }

    public void addStock(int qty) {
        stock += qty;
    }

    public void reduceStock(int qty) {
        if (qty <= stock) {
            stock -= qty;
        } else {
            throw new IllegalArgumentException("Stock insuficiente para " + name);
        }
    }

    public double getProfitPerUnit() {
        return price - cost;
    }

    public String getInfo() {
        return String.format("%s | Precio venta: $%.2f | Costo: $%.2f | Stock: %d",
                name, price, cost, stock);
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getCost() { return cost; }
    public int getStock() { return stock; }
}
