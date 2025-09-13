package com.example.meta23;

import com.example.meta23.Order;
import java.util.List;

public class FinanceService {
    public double calculateRevenue(List<Order> orders) {
        return orders.stream().mapToDouble(Order::calculateTotal).sum();
    }

    public double calculateProfit(List<Order> orders) {
        return orders.stream().mapToDouble(Order::calculateProfit).sum();
    }

    public void generateReport(List<Order> orders) {
        System.out.println("=== Reporte Financiero ===");
        System.out.println("Ventas totales: $" + calculateRevenue(orders));
        System.out.println("Ganancias: $" + calculateProfit(orders));
        System.out.println("Pérdidas estimadas: $" + (calculateRevenue(orders) - calculateProfit(orders)));
    }
}
