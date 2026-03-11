package resto;

import resto.logger.ConsoleLogger;
import resto.logger.KitchenDisplaySystemLogger;
import resto.logger.Logger;
import resto.model.*;
import resto.service.*;
import resto.ui.ConsoleMenu;
import resto.validation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);

        InventoryService inventoryService = context.getBean(InventoryService.class);
        RecipeService recipeService = context.getBean(RecipeService.class);
        ProcurementService procurementService = context.getBean(ProcurementService.class);
        Logger logger = context.getBean(Logger.class);

        initializeTestData(inventoryService, recipeService, procurementService, logger);

        // TODO: занятие 5 - инициализация логгеров (Console для системы, KDS для кухни)

        // TODO: занятие 5 - создание сервисов

        // TODO: занятие 4 - создание валидаторов

        // TODO: занятие 5 - создание зависимых сервисов

        // TODO: занятие 3 - запуск меню

    }

    private static void initializeTestData(InventoryService inventoryService,
                                          RecipeService recipeService,
                                          ProcurementService procurementService,
                                          Logger logger) {
        Ingredient beef = new Ingredient(
                UUID.randomUUID().toString(),
                "Говядина вырезка",
                IngredientCategory.MEAT,
                "kg",
                15.0
        );
        Ingredient cream = new Ingredient(
                UUID.randomUUID().toString(),
                "Сливки 20%",
                IngredientCategory.DAIRY,
                "l",
                11.0
        );
        Ingredient salt = new Ingredient(
                UUID.randomUUID().toString(),
                "Соль",
                IngredientCategory.SPICES,
                "g",
                0.5
        );

        inventoryService.addIngredient(beef);
        inventoryService.addIngredient(cream);
        inventoryService.addIngredient(salt);

        String recCode = UUID.randomUUID().toString();
        Recipe steak = new Recipe(recCode, "Стейк Рибай", DishCategory.MAIN_COURSE);
        steak.addIngredient(beef, 0.2, "kg");
        steak.addIngredient(cream, 0.05, "l");
        steak.addIngredient(salt, 5, "g");

        StorageZone fridge = new StorageZone(
                UUID.randomUUID().toString(),
                "Холодильник",
                ZoneType.REFRIGERATOR,
                100.0
        );
        inventoryService.addStorageZone(fridge);

        IngredientCategory[] categories = {IngredientCategory.MEAT, IngredientCategory.DAIRY};
        FoodVendor vendor = new FoodVendor(
                UUID.randomUUID().toString(),
                "Мяско",
                "Крутой Чел",
                "+71234567890",
                "kotenok123@mur.ru",
                categories
        );
        procurementService.addVendor(vendor);

        KitchenOrder order = new KitchenOrder(
                UUID.randomUUID().toString(),
                recCode,
                5,
                "Крутой чел" // работников у нас пока нет, только он
        );
        double costPerPortion = steak.calculateFoodCost(1);
        double costForFive = steak.calculateFoodCost(5);

        System.out.println("\nИнгредиенты:");
        System.out.println(beef);
        System.out.println(cream);
        System.out.println(salt);

        System.out.println("\nРецепт:");
        System.out.println(steak);
        System.out.println("Строки рецепта:");
        for (RecipeLine line : steak.getLines()) {
            System.out.println(line);
        }

        System.out.println("\nЗона хранения:");
        System.out.println(fridge);

        System.out.println("\nПоставщик:");
        System.out.println(vendor);

        System.out.println("\nКухонный заказ:");
        System.out.println(order);
        System.out.println("\nСебестоимость");
        System.out.printf("Одна порция: %.2f руб.%n", costPerPortion);
        System.out.printf("Пять порций: %.2f руб.%n", costForFive);

        logger.log("Инициализация тестовых данных завершена");
    }
}
