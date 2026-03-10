package resto.model;

public enum ZoneType {
    DRY_STORAGE,    // сухой склад (+20°C)
    REFRIGERATOR,   // холодильник (+2...+6°C)
    FREEZER,        // морозильник (-18...-12°C)
    WINE_CELLAR,    // винный погреб (+10...+14°C)
    BAR;            // барная зона
    
    // TODO: занятие 3 - добавить поле defaultTemperature для каждого типа
    // TODO: занятие 3 - добавить метод getRecommendedCategories() возвращающий IngredientCategory[]
}
