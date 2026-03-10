package resto.model;

import java.time.LocalDate;

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
        this.reserved = 0.0;
        this.receivedDate = LocalDate.now();
        this.expiryDate = expiryDate;
        this.isOpened = false;
    }

    // TODO: занятие 1 - добавить валидацию в конструктор (quantity > 0, expiryDate > today)

    public double getAvailable() {
        // TODO: занятие 1 - вернуть quantity - reserved
        return 0;
    }

    public void reserve(double amount) {
        // TODO: занятие 1 - проверить что getAvailable() >= amount, увеличить reserved
    }

    public void releaseReservation(double amount) {
        // TODO: занятие 1 - уменьшить reserved на amount (не меньше 0)
    }

    public void writeOff(double amount) {
        // TODO: занятие 1 - уменьшить quantity на amount, проверить что не меньше reserved
    }

    public boolean isExpired() {
        // TODO: занятие 1 - проверить что LocalDate.now() > expiryDate
        return false;
    }

    public int getDaysUntilExpiry() {
        // TODO: занятие 1 - рассчитать дней до expiryDate (может быть отрицательным если просрочено)
        return 0;
    }

    public void markOpened() {
        // TODO: занятие 1 - установить isOpened = true, скорректировать shelfLife
    }

    // Геттеры/сеттеры...
    public String getPositionId() { return positionId; }
    public void setPositionId(String positionId) { this.positionId = positionId; }
    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
    public StorageZone getZone() { return zone; }
    public void setZone(StorageZone zone) { this.zone = zone; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public double getReserved() { return reserved; }
    public void setReserved(double reserved) { this.reserved = reserved; }
    public LocalDate getReceivedDate() { return receivedDate; }
    public void setReceivedDate(LocalDate receivedDate) { this.receivedDate = receivedDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public boolean isOpened() { return isOpened; }
    public void setOpened(boolean opened) { isOpened = opened; }

    @Override
    public String toString() {
        // TODO: занятие 1 - сделать читаемый формат
        return "StockPosition[" + positionId + "] " + ingredient.getName() + ": " + quantity + " " + ingredient.getUnit();
    }
}
