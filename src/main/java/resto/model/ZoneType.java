package resto.model;

public enum ZoneType {
    DRY_STORAGE(20.0),    // сухой склад (+20°C)
    REFRIGERATOR(4.0),   // холодильник (+2...+6°C)
    FREEZER(-15.0),        // морозильник (-18...-12°C)
    WINE_CELLAR(12.0),    // винный погреб (+10...+14°C)
    BAR(20.0);            // барная зона

    private final double defaultTemperature;

    ZoneType(double defaultTemperature) {
        this.defaultTemperature = defaultTemperature;
    }

    public double getDefaultTemperature() {
        return defaultTemperature;
    }

    // TODO: занятие 3 - добавить метод getRecommendedCategories() возвращающий IngredientCategory[]
}
