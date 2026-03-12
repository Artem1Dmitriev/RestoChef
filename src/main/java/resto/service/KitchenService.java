package resto.service;

import resto.component.NormCalculator;
import resto.exception.*;
import resto.logger.Logger;
import resto.model.*;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
    private final Map<String, KitchenOrder> orders;
    private final InventoryService inventoryService;
    private final RecipeService recipeService;
    private final NormCalculator normCalculator;
    private final Logger logger;

    public KitchenService(InventoryService inventoryService, RecipeService recipeService, NormCalculator normCalculator, Logger logger) {
        this.inventoryService = inventoryService;
        this.recipeService = recipeService;
        this.normCalculator = normCalculator;
        this.logger = logger;
        this.orders = new HashMap<>();
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
        KitchenOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        try {
            return normCalculator.getViolations(order);
        } catch (RecipeNotFoundException | IngridientNotFoundInRecipeException e) {
            logger.log("Ошибка при расчёте нарушений: " + e.getMessage());
            return Collections.emptyMap();
        }
    }
    public boolean canChangeStatus(KitchenOrder order, KitchenStatus newStatus) {
        return order.getStatus().canTransitionTo(newStatus);
    }
    public void changeStatus(KitchenOrder order, KitchenStatus newStatus) {
        if (!canChangeStatus(order, newStatus)) {
            throw new IllegalStateException("Нельзя изменить  " + order.getStatus() + " на " + newStatus);
        }
        order.setStatus(newStatus);
        logger.log("Статус заказа " + order.getOrderId() + " изменён на " + newStatus);
        if (newStatus == KitchenStatus.READY) {
            order.setCompletedAt(LocalDateTime.now());
        }
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
