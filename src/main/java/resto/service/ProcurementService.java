package resto.service;

import resto.logger.Logger;
import resto.model.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ProcurementService {
    private final InventoryService inventoryService;
    private final Map<String, FoodVendor> vendors;
    private final Logger logger;

    public ProcurementService(InventoryService inventoryService, Logger logger) {
        this.inventoryService = inventoryService;
        this.vendors = new HashMap<>();
        this.logger = logger;
    }

    public Map<String, Double> generateOrderList() {
        // TODO: занятие 5 - найти ингредиенты где getAvailableStock() < minStockLevel
        // TODO: рассчитать дефицит (minStockLevel - available), вернуть Map<ingredientId, количество_к_заказу>
        return new HashMap<>();
    }

    public double calculateOrderCost(Map<String, Double> orderList) {
        // TODO: занятие 5 - для каждого ingredientId найти цену, умножить на количество, суммировать
        return 0;
    }

    public FoodVendor selectSupplier(String ingredientId) {
        // TODO: занятие 5 - найти ингредиент, выбрать поставщика по supplyCategories
        // TODO: вернуть поставщика с минимальным deliveryDays среди подходящих
        return null;
    }

    public PurchaseOrder createPurchaseOrder(String supplierId, Map<String, Double> items) {
        // TODO: занятие 5 - создать заказ поставщику (класс PurchaseOrder не описан, создать если нужно)
        return null;
    }

    public LocalDate getOptimalOrderDate(String ingredientId) {
        // TODO: занятие 5 - рассчитать: expiryDate - deliveryDays - buffer (3 дня)
        // TODO: для этого нужно знать текущий запас и срок годности
        return LocalDate.now();
    }

    public void addVendor(FoodVendor vendor) {
        // TODO: занятие 2 - добавить поставщика в vendors
    }
}

// TODO: занятие 5 - создать класс PurchaseOrder если требуется для полноты
class PurchaseOrder {
    // TODO: поля: orderId, supplierId, items, orderDate, deliveryDate, status, totalAmount
}
