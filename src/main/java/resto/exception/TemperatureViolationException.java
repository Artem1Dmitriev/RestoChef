package resto.exception;

import resto.model.IngredientCategory;

public class TemperatureViolationException extends Exception {
    private final IngredientCategory category;
    private final double currentTemp;
    private final double requiredTemp;

    public TemperatureViolationException(IngredientCategory category, 
                                        double currentTemp, double requiredTemp) {
        super(String.format("Нарушение температуры для %s: текущая %.1f°C, требуется %.1f°C", 
                category, currentTemp, requiredTemp));
        this.category = category;
        this.currentTemp = currentTemp;
        this.requiredTemp = requiredTemp;
    }

    public IngredientCategory getCategory() { return category; }
    public double getCurrentTemp() { return currentTemp; }
    public double getRequiredTemp() { return requiredTemp; }
}
