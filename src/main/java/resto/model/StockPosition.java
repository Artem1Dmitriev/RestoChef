package resto.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.Data;

@Data
public class StockPosition {
    private String positionId;
    private Ingredient ingredient;
    private StorageZone zone;
    private double quantity;
    private double reserved;
    private LocalDate receivedDate;
    private LocalDate expiryDate;
    private boolean isOpened;

    public StockPosition(String positionId, Ingredient ingredient, 
                        StorageZone zone, double quantity, LocalDate expiryDate) {
        this.positionId = positionId;
        this.ingredient = ingredient;
        this.zone = zone;
        this.quantity = quantity;
        // может ли reserved быть больше quantity?
        this.reserved = 0.0;
        this.receivedDate = LocalDate.now();
        this.isOpened = false;
        setExpiryDate(expiryDate);
        setQuantity(quantity);
    }
    public double getAvailable() {
        return quantity - reserved;
    }

    public void reserve(double amount) {
        double available = getAvailable();
        if (available >= amount) {
            reserved += amount;
        }
        else {
            throw new IllegalArgumentException("Зарезервировано. Можно потратить " + available);
        }
    }

    public void releaseReservation(double amount) {
        if (amount > reserved) {
            throw new IllegalArgumentException("Столько и не просили");
        }
        reserved -= amount;
    }

    public void writeOff(double amount) {
        double nextQuantity = quantity - amount;
        if (nextQuantity < reserved) {
            throw new IllegalArgumentException("Нельзя брать с резерва");
        }
        quantity = nextQuantity;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public long getDaysUntilExpiry() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }

    public void markOpened() {
        // TODO: занятие 1 - установить isOpened = true, скорректировать shelfLife
    }

    public void setQuantity(double quantity) {
        if (quantity <= 0.0) {
            throw new IllegalArgumentException("Количество не может быть меньше нуля");
        }
        this.quantity = quantity;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Уже пропало :(");
        }
        this.expiryDate = expiryDate;
    }

}
