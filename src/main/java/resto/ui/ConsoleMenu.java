package resto.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import resto.exception.ExpiredIngredientException;
import resto.exception.IngridientNotFoundException;
import resto.exception.NormViolationException;
import resto.exception.TemperatureViolationException;
import resto.logger.Logger;
import resto.model.*;
import resto.service.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConsoleMenu implements CommandLineRunner {
    private final InventoryService inventoryService;
    private final KitchenService kitchenService;
    private final RecipeService recipeService;
    private final ConsoleReader consoleReader;
    private final ConsoleWriter consoleWriter;
    private final ProcurementService procurementService;
    private final Logger logger;


    @Override public void run(String... args) {
        logger.log("Консольное меню запущено");
        while (true) {
            consoleWriter.printMenu();
            int choice = consoleReader.readIntInput("Выберите пункт:");
            try {
                processChoice(choice);
            } catch (Exception e) {
                consoleWriter.printError(e.getMessage());
                logger.log("Ошибка в меню: " + e.getMessage());
            }
        }
    }

    private void processChoice(int choice) throws IngridientNotFoundException {
        switch (choice) {
            case 1 -> addIngredient();
            case 2 -> createStorageZone();
            case 3 -> acceptDelivery();
            case 4 -> moveIngredient();
            case 5 -> createRecipe();
            case 6 -> addIngredientToRecipe();
            case 7 -> calculateFoodCost();
            case 8 -> createKitchenOrder();
            case 9 -> approveOrder();
            case 10 -> pickIngredients();
            case 11 -> deliverToKitchen();
            case 12 -> recordActualConsumption();
            case 13 -> completeOrder();
            case 14 -> reportNormViolations();
            case 15 -> reportLowStock();
            case 16 -> createPurchaseOrder();
            case 0 -> exit();
            default -> consoleWriter.printMessage("Неверный выбор");
        }
    }
    private void addIngredient() {
        consoleWriter.printMessage("\nДобавление ингредиента");
        String id = UUID.randomUUID().toString();
        String name = consoleReader.readStringInput("Название: ");
        String categoryStr = consoleReader.readStringInput("Категория (MEAT,FISH,DAIRY,VEGETABLES,FRUITS,GRAINS,SPICES,OILS,BEVERAGES): ");
        IngredientCategory category;
        try {
            category = IngredientCategory.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            consoleWriter.printError("Неверная категория");
            return;
        }
        String unit = consoleReader.readStringInput("Единица измерения (kg,g,l,ml,pcs): ");
        double cost = consoleReader.readDoubleInput("Цена за единицу: ");
        Ingredient ingredient = new Ingredient(id, name, category, unit, cost);
        inventoryService.addIngredient(ingredient);
        consoleWriter.printSuccess("Ингредиент добавлен");
    }

    private void createStorageZone() {
        consoleWriter.printMessage("\nСоздание зоны хранения");
        String id = UUID.randomUUID().toString();
        String name = consoleReader.readStringInput("Название: ");
        String zoneTypeStr = consoleReader.readStringInput("Тип (DRY_STORAGE, REFRIGERATOR, FREEZER, WINE_CELLAR, BAR): ");
        ZoneType zoneType;
        try {
            zoneType = ZoneType.valueOf(zoneTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            consoleWriter.printError("Неверный тип зоны");
            return;
        }
        double capacity = consoleReader.readDoubleInput("Вместимость: ");
        StorageZone zone = new StorageZone(id, name, zoneType, capacity);
        inventoryService.addStorageZone(zone);
        consoleWriter.printSuccess("Зона хранения создана");
        consoleWriter.printMessage(zone.toString());
    }

    private void acceptDelivery() throws IngridientNotFoundException {
        consoleWriter.printMessage("\nПриём поставки ингредиента");
        String ingredientId = consoleReader.readStringInput("ID ингредиента: ");
        double quantity = consoleReader.readDoubleInput("Количество: ");
        String expiryStr = consoleReader.readStringInput("Срок годности (ГГГГ-ММ-ДД): ");
        LocalDate expiryDate;
        try {
            expiryDate = LocalDate.parse(expiryStr);
        } catch (Exception e) {
            consoleWriter.printError("Неверный формат даты. Используйте ГГГГ-ММ-ДД");
            return;
        }
        String zoneId = consoleReader.readStringInput("ID зоны хранения: ");

        Ingredient ingredient = inventoryService.getIngredient(ingredientId);
        StorageZone zone = inventoryService.getZone(zoneId);
        try {
            StockPosition position = inventoryService.acceptIngredient(ingredient, quantity, expiryDate, zone);
            consoleWriter.printSuccess("Поставка принята");
            consoleWriter.printMessage("Позиция: " + position.getPositionId());
        } catch (TemperatureViolationException | ExpiredIngredientException e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void moveIngredient() {
        consoleWriter.printMessage("\nПеремещение ингредиента между зонами");
        String positionId = consoleReader.readStringInput("ID позиции: ");
        String toZoneId = consoleReader.readStringInput("ID целевой зоны: ");
        double amount = consoleReader.readDoubleInput("Количество для перемещения: ");

        StorageZone toZone = inventoryService.getZone(toZoneId);
        try {
            inventoryService.transferToZone(positionId, toZoneId, amount);
            consoleWriter.printSuccess("Перемещение выполнено");
        } catch (TemperatureViolationException e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void createRecipe() {
        consoleWriter.printMessage("\nСоздание рецепта");
        String code = consoleReader.readStringInput("Код рецепта: ");
        String name = consoleReader.readStringInput("Название блюда: ");
        String categoryStr = consoleReader.readStringInput("Категория (HOT_APPETIZER, COLD_APPETIZER, SOUP, MAIN_COURSE, DESSERT, BEVERAGE): ");
        DishCategory category;
        try {
            category = DishCategory.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            consoleWriter.printError("Неверная категория");
            return;
        }
        Recipe recipe = recipeService.createRecipe(code, name, category);
        consoleWriter.printSuccess("Рецепт создан");
        consoleWriter.printMessage(recipe.toString());
    }
    private void addIngredientToRecipe() {
        consoleWriter.printMessage("\nДобавление ингредиента в рецепт");
        String recipeCode = consoleReader.readStringInput("Код рецепта: ");
        String ingredientId = consoleReader.readStringInput("ID ингредиента: ");
        double quantity = consoleReader.readDoubleInput("Количество: ");
        String unit = consoleReader.readStringInput("Единица измерения: ");
        try {
            recipeService.addIngredientToRecipe(recipeCode, ingredientId, quantity, unit);
            consoleWriter.printSuccess("Ингредиент добавлен в рецепт");
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void calculateFoodCost() {
        consoleWriter.printMessage("\nРасчёт себестоимости блюда");
        String recipeCode = consoleReader.readStringInput("Код рецепта: ");
        int portions = consoleReader.readIntInput("Количество порций: ");
        try {
            double cost = recipeService.calculateFoodCost(recipeCode, portions);
            consoleWriter.printMessage(String.format("Себестоимость %d порций: %.2f руб.", portions, cost));
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void createKitchenOrder() {
        consoleWriter.printMessage("\nСоздание заказа на кухню");
        String recipeCode = consoleReader.readStringInput("Код рецепта: ");
        int portions = consoleReader.readIntInput("Количество порций: ");
        String requestedBy = consoleReader.readStringInput("Кто запросил: ");
        KitchenOrder order = kitchenService.createKitchenOrder(recipeCode, portions, requestedBy);
        consoleWriter.printSuccess("Заказ создан");
        consoleWriter.printMessage(order.toString());
    }
    private void approveOrder() {
        consoleWriter.printMessage("\nУтверждение заказа");
        String orderId = consoleReader.readStringInput("ID заказа: ");
        String chefName = consoleReader.readStringInput("Имя шеф-повара: ");
        try {
            kitchenService.approveOrder(orderId, chefName);
            consoleWriter.printSuccess("Заказ утверждён");
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void pickIngredients() {
        consoleWriter.printMessage("\nСбор ингредиентов со склада");
        String orderId = consoleReader.readStringInput("ID заказа: ");
        try {
            kitchenService.pickIngredients(orderId);
            consoleWriter.printSuccess("Ингредиенты собраны");
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void deliverToKitchen() {
        consoleWriter.printMessage("\nПередача на кухню");
        String orderId = consoleReader.readStringInput("ID заказа: ");
        try {
            kitchenService.deliverToKitchen(orderId);
            consoleWriter.printSuccess("Заказ передан на кухню");
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void recordActualConsumption() {
        consoleWriter.printMessage("\nФиксация фактического расхода");
        String orderId = consoleReader.readStringInput("ID заказа: ");
        String ingredientId = consoleReader.readStringInput("ID ингредиента: ");
        double actualQty = consoleReader.readDoubleInput("Фактическое количество: ");
        try {
            kitchenService.recordActualConsumption(orderId, ingredientId, actualQty);
            consoleWriter.printSuccess("Расход зафиксирован");
        } catch (NormViolationException e) {
            consoleWriter.printError("Нарушение нормы: " + e.getMessage());
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void completeOrder() {
        consoleWriter.printMessage("\nЗавершение приготовления");
        String orderId = consoleReader.readStringInput("ID заказа: ");
        try {
            kitchenService.completeOrder(orderId);
            consoleWriter.printSuccess("Заказ завершён");
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void createPurchaseOrder() {
        consoleWriter.printMessage("В разработке");
    }
    private void reportLowStock() {
        consoleWriter.printMessage("\nОтчёт о товарах к заказу");
        Map<String, Double> orderList = procurementService.generateOrderList();
        if (orderList.isEmpty()) {
            consoleWriter.printMessage("Всех товаров достаточно");
        } else {
            consoleWriter.printMessage("Товары ниже минимального уровня:");
            for (Map.Entry<String, Double> entry : orderList.entrySet()) {
                consoleWriter.printMessage("  " + entry.getKey() + ": нужно " + entry.getValue());
            }
            double totalCost = procurementService.calculateOrderCost(orderList);
            consoleWriter.printMessage(String.format("Общая стоимость заказа: %.2f руб.", totalCost));
        }
    }
    private void reportNormViolations() {
        consoleWriter.printMessage("\nОтчёт о превышении норм расхода");
        String orderId = consoleReader.readStringInput("ID заказа: ");
        try {
            Map<String, Double> violations = kitchenService.getNormViolations(orderId);
            if (violations.isEmpty()) {
                consoleWriter.printMessage("Нарушений нет");
            } else {
                consoleWriter.printMessage("Ингредиенты с превышением нормы:");
                for (Map.Entry<String, Double> entry : violations.entrySet()) {
                    consoleWriter.printMessage("  " + entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (Exception e) {
            consoleWriter.printError("Ошибка: " + e.getMessage());
        }
    }
    private void exit() {
        consoleWriter.printMessage("Выход...");
        logger.log("Меню завершено");
        System.exit(0);
    }
}
