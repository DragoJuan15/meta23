package com.example.meta23;

import com.example.meta23.Product;
import com.example.meta23.Order;
import com.example.meta23.FinanceService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class ERPController {

    @FXML
    private TextField nameField, priceField, costField, stockField;
    @FXML
    private TextField productNameField; // nuevo campo para vender por nombre
    @FXML
    private TextArea logArea;
    @FXML
    private Label statusLabel;

    private List<Product> inventory = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private long orderIdCounter = 1;
    private long productIdCounter = 1;

    private FinanceService financeService = new FinanceService();

    @FXML
    protected void onAddProductClick() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            double cost = Double.parseDouble(costField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Product p = new Product(productIdCounter++, name, price, cost, stock);
            inventory.add(p);

            logArea.appendText("Producto agregado:\n   " + p.getInfo() + "\n");
            statusLabel.setText("Producto agregado con éxito");
        } catch (Exception e) {
            statusLabel.setText("Error al agregar producto");
        }
    }

    @FXML
    protected void onCreateOrderClick() {
        String requestedName = productNameField.getText();
        if (requestedName == null || requestedName.isBlank()) {
            statusLabel.setText("Escribe el nombre del producto a vender");
            return;
        }

        Product found = inventory.stream()
                .filter(p -> p.getName().equalsIgnoreCase(requestedName))
                .findFirst()
                .orElse(null);

        if (found == null) {
            statusLabel.setText("Producto no encontrado en inventario");
            return;
        }

        if (found.getStock() <= 0) {
            statusLabel.setText("No hay stock disponible de " + found.getName());
            return;
        }

        // Crear orden con el producto encontrado
        Order order = new Order(orderIdCounter++);
        found.reduceStock(1);
        order.addProduct(found);
        orders.add(order);

        logArea.appendText("🛒 Orden #" + orderIdCounter +
                " creada con producto: " + found.getName() +
                " | Total: $" + order.calculateTotal() +
                " | Stock restante: " + found.getStock() + "\n");

        statusLabel.setText("Orden creada con " + found.getName());
    }

    @FXML
    protected void onShowInventoryClick() {
        logArea.appendText("\nInventario actual:\n");
        for (Product p : inventory) {
            logArea.appendText("   - " + p.getInfo() + "\n");
        }
        statusLabel.setText("Inventario mostrado");
    }

    @FXML
    protected void onShowFinanceClick() {
        double revenue = financeService.calculateRevenue(orders);
        double profit = financeService.calculateProfit(orders);
        double losses = revenue - profit;

        logArea.appendText("\nReporte Financiero:\n");
        logArea.appendText("   Ingresos totales: $" + revenue + "\n");
        logArea.appendText("   Ganancias netas: $" + profit + "\n");
        logArea.appendText("   Pérdidas (costos): $" + losses + "\n\n");

        statusLabel.setText("Reporte generado");
    }
}
