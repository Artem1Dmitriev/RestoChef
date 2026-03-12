package resto.model;

public enum IngredientCategory {
    MEAT(2.0),       // мясо, хранить при +2°C
    FISH(0.0),       // рыба, хранить при 0°C
    DAIRY(4.0),      // молочные, хранить при +4°C
    VEGETABLES(10.0),// овощи, хранить при +10°C
    FRUITS(10.0),    // фрукты, хранить при +10°C
    GRAINS(15.0),    // крупы, мучные, хранить при +15°C
    SPICES(15.0),    // специи, хранить при +15°C
    OILS(15.0),      // масла, жиры, хранить при +15°C
    BEVERAGES(15.0); // напитки, хранить при +15°C

    private final double storageTemperature;

    IngredientCategory(double storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    public double getStorageTemperature() {
        return storageTemperature;
    }

    public boolean requiresColdStorage() {
        return this == MEAT || this == FISH || this == DAIRY;
    }
}