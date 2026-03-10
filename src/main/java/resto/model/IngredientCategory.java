package resto.model;

public enum IngredientCategory {
    MEAT,       // мясо
    FISH,       // рыба
    DAIRY,      // молочные
    VEGETABLES, // овощи
    FRUITS,     // фрукты
    GRAINS,     // крупы, мучные
    SPICES,     // специи
    OILS,       // масла, жиры
    BEVERAGES;  // напитки
    
    // TODO: занятие 3 - добавить метод requiresColdStorage() для MEAT, FISH, DAIRY
    // TODO: занятие 3 - добавить поле storageTemperature с рекомендуемой температурой
}
