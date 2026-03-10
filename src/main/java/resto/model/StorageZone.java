package resto.model;

public class StorageZone {
    private String id;
    private String name;
    private ZoneType zoneType;
    private double temperature;
    private double capacity;
    private double currentLoad;

    public StorageZone(String id, String name, ZoneType zoneType, double capacity) {
        this.id = id;
        this.name = name;
        this.zoneType = zoneType;
        this.capacity = capacity;
        this.currentLoad = 0.0;
        // TODO: занятие 1 - установить температуру по умолчанию в зависимости от zoneType
        // TODO: REFRIGERATOR: +4, FREEZER: -15, WINE_CELLAR: +12, DRY_STORAGE/BAR: +20
    }

    // TODO: занятие 1 - добавить валидацию в сеттеры (capacity > 0)

    public boolean canAccept(double amount) {
        // TODO: занятие 1 - проверить что currentLoad + amount <= capacity
        return false;
    }

    public void addLoad(double amount) {
        // TODO: занятие 1 - увеличить currentLoad на amount
    }

    public void removeLoad(double amount) {
        // TODO: занятие 1 - уменьшить currentLoad на amount
    }

    public boolean isTemperatureSafe(IngredientCategory category) {
        // TODO: занятие 1 - проверить температуру для категории:
        // TODO: MEAT/FISH/DAIRY: только REFRIGERATOR/FREEZER (temp <= +6 или <= -12)
        // TODO: WINE_CELLAR: только для BEVERAGES (вино)
        // TODO: DRY_STORAGE: для остальных категорий
        return false;
    }

    // Геттеры/сеттеры...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ZoneType getZoneType() { return zoneType; }
    public void setZoneType(ZoneType zoneType) { this.zoneType = zoneType; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }
    public double getCurrentLoad() { return currentLoad; }
    public void setCurrentLoad(double currentLoad) { this.currentLoad = currentLoad; }

    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат вывода
        return "StorageZone[" + id + "] " + name + " (" + zoneType + ")";
    }
}
