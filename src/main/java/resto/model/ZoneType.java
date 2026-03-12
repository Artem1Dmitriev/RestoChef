package resto.model;

import java.util.Map;

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

    public IngredientCategory[] getRecommendedCategories() {
        return switch (this) {
            case DRY_STORAGE -> new IngredientCategory[] {
                    IngredientCategory.VEGETABLES,
                    IngredientCategory.FRUITS,
                    IngredientCategory.GRAINS,
                    IngredientCategory.SPICES,
                    IngredientCategory.OILS,
                    IngredientCategory.BEVERAGES
            };
            case REFRIGERATOR -> new IngredientCategory[] {
                    IngredientCategory.MEAT,
                    IngredientCategory.FISH,
                    IngredientCategory.DAIRY,
                    IngredientCategory.VEGETABLES,
                    IngredientCategory.FRUITS,
                    IngredientCategory.BEVERAGES
            };
            case FREEZER -> new IngredientCategory[] {
                    IngredientCategory.MEAT,
                    IngredientCategory.FISH
            };
            case WINE_CELLAR -> new IngredientCategory[] {
                    IngredientCategory.BEVERAGES
            };
            case BAR -> new IngredientCategory[] {
                    IngredientCategory.BEVERAGES
            };
        };

    }
}
