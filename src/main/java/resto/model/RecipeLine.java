package resto.model;

public class RecipeLine {
    private String id;
    private Ingredient ingredient;
    private double quantityPerPortion;
    private String unit;
    private double normDeviationPercent;

    public RecipeLine(Ingredient ingredient, double quantityPerPortion, String unit) {
        this.id = ingredient.getId() + "-line";
        this.ingredient = ingredient;
        this.quantityPerPortion = quantityPerPortion;
        this.unit = unit;
        this.normDeviationPercent = 5.0; // по умолчанию 5%
    }

    // TODO: занятие 1 - добавить конвертацию единиц если unit != ingredient.getUnit()

    public double calculateForPortions(int portions) {
        // TODO: занятие 1 - вернуть quantityPerPortion * portions с конвертацией единиц
        return 0;
    }

    public double calculateCostForPortions(int portions) {
        // TODO: занятие 1 - рассчитать стоимость: calculateForPortions(portions) * ingredient.getCostPerUnit()
        return 0;
    }

    public boolean isWithinNorm(double actual, int portions) {
        // TODO: занятие 1 - проверить что отклонение факта от нормы <= normDeviationPercent
        // TODO: planned = calculateForPortions(portions), deviation = |actual - planned| / planned * 100
        return false;
    }

    // Геттеры/сеттеры...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
    public double getQuantityPerPortion() { return quantityPerPortion; }
    public void setQuantityPerPortion(double quantityPerPortion) { this.quantityPerPortion = quantityPerPortion; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public double getNormDeviationPercent() { return normDeviationPercent; }
    public void setNormDeviationPercent(double normDeviationPercent) { this.normDeviationPercent = normDeviationPercent; }

    @Override
    public String toString() {
        // TODO: занятие 1 - сделать читаемый формат
        return "RecipeLine: " + ingredient.getName() + " " + quantityPerPortion + " " + unit + "/порция";
    }
}
