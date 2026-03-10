package resto;

import resto.logger.ConsoleLogger;
import resto.logger.KitchenDisplaySystemLogger;
import resto.logger.Logger;
import resto.model.*;
import resto.service.*;
import resto.ui.ConsoleMenu;
import resto.validation.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // TODO: занятие 5 - инициализация логгеров (Console для системы, KDS для кухни)

        // TODO: занятие 5 - создание сервисов

        // TODO: занятие 4 - создание валидаторов

        // TODO: занятие 5 - создание зависимых сервисов

        // TODO: занятие 1 - инициализация тестовых данных

        // TODO: занятие 3 - запуск меню

    }

    private static void initializeTestData(InventoryService inventoryService,
                                          RecipeService recipeService,
                                          ProcurementService procurementService,
                                          Logger logger) {
        // TODO: занятие 1 - создать 2 ингредиента (например, "Говядина вырезка" и "Сливки 20%")
        
        // TODO: занятие 1 - создать рецепт с 3 строками (например, "Стейк Рибай")
        
        // TODO: занятие 1 - создать заказ на 5 порций
        
        // TODO: занятие 1 - создать зоны хранения (REFRIGERATOR для мяса/молочки)
        
        // TODO: занятие 1 - создать поставщика
        
        // TODO: занятие 1 - вывести в консоль информацию о созданных объектах
        // TODO: рассчитать и вывести себестоимость блюда
    }
}
