package resto.service;

import resto.exception.*;
import resto.logger.Logger;
import resto.model.*;
import resto.validation.NormValidator;

import java.time.LocalDateTime;
import java.util.*;

public class KitchenService {
    private final Map<String, KitchenOrder> orders;
    private final InventoryService inventoryService;
    private final RecipeService recipeService;
    private final NormValidator normValidator;
    private final Logger logger;

    public KitchenService(InventoryService inventoryService, 
                         RecipeService recipeService,
                         NormValidator normValidator,
                         Logger logger) {
        this.orders = new HashMap<>();
        this.inventoryService = inventoryService;
        this.recipeService = recipeService;
        this.normValidator = normValidator;
        this.logger = logger;
    }

    public KitchenOrder createKitchenOrder(String recipeCode, int portions, String requestedBy) {
        // TODO: занятие 5 - создать KitchenOrder с уникальным orderId (формат КУХ-2024-XXXX)
        // TODO: сохранить в orders, залогировать создание
        return null;
    }

    public void approveOrder(String orderId, String chefName) 
            throws OrderNotFoundException, InsufficientStockException {
        // TODO: занятие 6 - сменить статус REQUESTED→APPROVED
        // TODO: проверить canPrepare через recipeService, иначе бросить InsufficientStockException
        // TODO: зарезервировать ингредиенты через inventoryService.reserveForOrder()
        // TODO: установить approvedBy, залогировать
    }

    public void pickIngredients(String orderId) throws OrderNotFoundException {
        // TODO: занятие 6 - сменить статус APPROVED→PICKED
        // TODO: физически собрать ингредиенты со склада (перемещение в зону кухни)
        // TODO: залогировать
    }

    public void deliverToKitchen(String orderId) throws OrderNotFoundException {
        // TODO: занятие 6 - сменить статус PICKED→DELIVERED_TO_KITCHEN
        // TODO: залогировать
    }

    public void startPreparation(String orderId, String cookName) throws OrderNotFoundException {
        // TODO: занятие 6 - сменить статус ACCEPTED→PREPARING
        // TODO: залогировать начало приготовления
    }

    public void recordActualConsumption(String orderId, String ingredientId, 
                                       double actualQty) throws OrderNotFoundException, 
                                       NormViolationException {
        // TODO: занятие 6 - зафиксировать факт в KitchenOrder.recordActualConsumption()
        // TODO: проверить норму через normValidator, бросить NormViolationException если >5%
        // TODO: если нарушение - уведомить шефа через logger (KitchenDisplaySystemLogger)
    }

    public void completeOrder(String orderId) throws OrderNotFoundException, 
            InvalidOrderStatusException, NormViolationException {
        // TODO: занятие 6 - проверить статус PREPARING, иначе InvalidOrderStatusException
        // TODO: проверить что все ингредиенты списаны, нет критических нарушений
        // TODO: списать зарезервированное через inventoryService.writeOffConsumption()
        // TODO: сменить статус на READY, установить completedAt, залогировать
    }

    public Map<String, Double> getNormViolations(String orderId) throws OrderNotFoundException {
        // TODO: занятие 6 - вернуть ингредиенты с превышением нормы (>5%)
        return new HashMap<>();
    }

    public KitchenOrder getOrderById(String orderId) {
        // TODO: занятие 2 - поиск в orders
        return null;
    }

    public void writeOffIngredients(String orderId, Map<String, Double> actualConsumption) 
            throws OrderNotFoundException, NormViolationException {
        // TODO: занятие 6 - для каждого ингредиента проверить норму и списать
        // TODO: при превышении нормы - уведомление шефу
    }
}
