package resto.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class StorageZone {
    private String id;
    private String name;
    private ZoneType zoneType;
    @Setter(AccessLevel.NONE)
    private double temperature;
    private double capacity;
    @Setter(AccessLevel.NONE)
    private double currentLoad;

    public StorageZone(String id, String name, ZoneType zoneType, double capacity) {
        this.id = id;
        this.name = name;
        setZoneType(zoneType);
        setCapacity(capacity);
        this.currentLoad = 0.0;
    }

    public boolean canAccept(double amount) {
        return currentLoad + amount <= capacity;
    }

    public void addLoad(double amount) {
        if (!canAccept(amount) || amount < 0) {
            throw new IllegalArgumentException("Попробовать можно, но оно того стоит?");
        }
        currentLoad += amount;
    }

    public void removeLoad(double amount) {
        if (currentLoad < amount || amount < 0) {
            throw new IllegalArgumentException("Перебор по оптимизации");
        }
        currentLoad -= amount;
    }

    public boolean isTemperatureSafe(IngredientCategory category) {
        return switch (category) {
            case MEAT, FISH, DAIRY -> zoneType == ZoneType.REFRIGERATOR || zoneType == ZoneType.FREEZER;
            case BEVERAGES -> zoneType == ZoneType.WINE_CELLAR;
            default -> zoneType == ZoneType.DRY_STORAGE;
        };
    }

    // может быть стоит проверить на capacity >= currentLoad, но пока опустим
    public void setCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше нуля");
        }
        this.capacity = capacity;
    }

    private void setZoneType(ZoneType zoneType) {
        this.zoneType = zoneType;
        this.temperature = zoneType.getDefaultTemperature();
    }
}
