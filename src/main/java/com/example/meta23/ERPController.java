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
            logArea.appendText("Producto agregado: " + p.getInfo() + "\n");
            statusLabel.setText("Producto agregado");
        } catch (Exception e) {
            statusLabel.setText("Error al agregar producto");
        }
    }

    @FXML
    protected void onCreateOrderClick() {
        if (inventory.isEmpty()) {
            statusLabel.setText("No hay productos en inventario");
            return;
        }
        Order order = new Order(orderIdCounter++);
        // Simulación: se agrega el primer producto
        Product p = inventory.get(0);
        if (p.getStock() > 0) {
            p.updateStock(-1);
            order.addProduct(p);
            orders.add(order);
            logArea.appendText("Orden creada con producto: " + p.getName() +
                    " | Total: $" + order.calculateTotal() + "\n");
            statusLabel.setText("Orden creada");
        } else {
            statusLabel.setText("Stock insuficiente de " + p.getName());
        }
    }

    @FXML
    protected void onShowFinanceClick() {
        double revenue = financeService.calculateRevenue(orders);
        double profit = financeService.calculateProfit(orders);
        double losses = revenue - profit;

        logArea.appendText("=== Reporte Financiero ===\n");
        logArea.appendText("Ingresos: $" + revenue + "\n");
        logArea.appendText("Ganancias: $" + profit + "\n");
        logArea.appendText("Pérdidas: $" + losses + "\n\n");

        statusLabel.setText("Reporte generado");
    }
}
