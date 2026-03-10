package resto.ui;

import resto.service.*;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner;
    private final InventoryService inventoryService;
    private final KitchenService kitchenService;
    private final RecipeService recipeService;
    private final ProcurementService procurementService;

    public ConsoleMenu(InventoryService inventoryService, 
                       KitchenService kitchenService,
                       RecipeService recipeService,
                       ProcurementService procurementService) {
        this.scanner = new Scanner(System.in);
        this.inventoryService = inventoryService;
        this.kitchenService = kitchenService;
        this.recipeService = recipeService;
        this.procurementService = procurementService;
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = readIntInput("Выберите пункт: ");
            
            try {
                switch (choice) {
                    case 1:
                        // TODO: занятие 2 - Добавить ингредиент в справочник
                        break;
                    case 2:
                        // TODO: занятие 2 - Создать зону хранения
                        break;
                    case 3:
                        // TODO: занятие 2 - Принять поставку ингредиента
                        break;
                    case 4:
                        // TODO: занятие 2 - Переместить ингредиент между зонами
                        break;
                    case 5:
                        // TODO: занятие 2 - Создать рецептуру блюда
                        break;
                    case 6:
                        // TODO: занятие 5 - Добавить ингредиент в рецепт
                        break;
                    case 7:
                        // TODO: занятие 5 - Рассчитать себестоимость блюда
                        break;
                    case 8:
                        // TODO: занятие 5 - Создать заказ на кухню
                        break;
                    case 9:
                        // TODO: занятие 6 - Утвердить заказ (шеф)
                        break;
                    case 10:
                        // TODO: занятие 6 - Собрать ингредиенты со склада
                        break;
                    case 11:
                        // TODO: занятие 6 - Передать на кухню
                        break;
                    case 12:
                        // TODO: занятие 6 - Зафиксировать фактический расход
                        break;
                    case 13:
                        // TODO: занятие 6 - Завершить приготовление
                        break;
                    case 14:
                        // TODO: занятие 5 - Отчёт: превышение норм расхода
                        break;
                    case 15:
                        // TODO: занятие 5 - Отчёт: товары к заказу (ниже точки заказа)
                        break;
                    case 16:
                        // TODO: занятие 5 - Сформировать заявку поставщику
                        break;
                    case 0:
                        System.out.println("Выход...");
                        return;
                    default:
                        System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                // TODO: занятие 4 - обработка исключений с выводом понятного сообщения пользователю
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== RestoChef ===");
        System.out.println("1. Добавить ингредиент в справочник");
        System.out.println("2. Создать зону хранения");
        System.out.println("3. Принять поставку ингредиента");
        System.out.println("4. Переместить ингредиент между зонами");
        System.out.println("5. Создать рецептуру блюда");
        System.out.println("6. Добавить ингредиент в рецепт");
        System.out.println("7. Рассчитать себестоимость блюда");
        System.out.println("8. Создать заказ на кухню");
        System.out.println("9. Утвердить заказ (шеф)");
        System.out.println("10. Собрать ингредиенты со склада");
        System.out.println("11. Передать на кухню");
        System.out.println("12. Зафиксировать фактический расход");
        System.out.println("13. Завершить приготовление");
        System.out.println("14. Отчёт: превышение норм расхода");
        System.out.println("15. Отчёт: товары к заказу");
        System.out.println("16. Сформировать заявку поставщику");
        System.out.println("0. Выход");
    }

    private int readIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    private double readDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Введите число!");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
