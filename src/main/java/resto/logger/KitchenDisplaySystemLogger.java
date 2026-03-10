package resto.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class KitchenDisplaySystemLogger implements Logger {
    private static final DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern("HH:mm:ss");
    private final List<String> kitchenAlerts;

    public KitchenDisplaySystemLogger() {
        this.kitchenAlerts = new ArrayList<>();
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String alert = "[" + timestamp + "] [KDS] " + message;
        kitchenAlerts.add(alert);
        System.out.println(alert); // Имитация вывода на дисплей кухни
    }

    public void notifyChef(String message) {
        // TODO: занятие 6 - специальное уведомление шеф-повару
        log("[ВНИМАНИЕ ШЕФУ] " + message);
    }

    public List<String> getKitchenAlerts() {
        return new ArrayList<>(kitchenAlerts);
    }

    public void clearAlerts() {
        kitchenAlerts.clear();
    }
}
