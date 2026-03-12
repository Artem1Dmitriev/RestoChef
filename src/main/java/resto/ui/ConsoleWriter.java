package resto.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsoleWriter {

    public void printMenu() {
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

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(String error) {
        System.err.println("Ошибка: " + error);
    }

    public void printSuccess(String message) {
        System.out.println("[OK] " + message);
    }
}
