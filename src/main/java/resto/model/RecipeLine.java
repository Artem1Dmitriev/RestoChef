package resto.model;

import lombok.Data;
import resto.component.UnitConversion;

// TODO: убрать unit. Приравнивать будем в RecipeService.addIngridientToRecipe
@Data
public class RecipeLine {
    private String id;
    private Ingredient ingredient;
    private double quantityPerPortion;
    private double normDeviationPercent;

    public RecipeLine(Ingredient ingredient, double quantityPerPortion) {
        this.id = ingredient.getId() + "-line";
        this.ingredient = ingredient;
        this.quantityPerPortion = quantityPerPortion;
        this.normDeviationPercent = 5.0; // по умолчанию 5%
    }

    public double calculateForPortions(int portions) {
        return quantityPerPortion * portions;
    }

    public double calculateCostForPortions(int portions) {
        return calculateForPortions(portions) * ingredient.getCostPerUnit();
    }

    public boolean isWithinNorm(double actual, int portions) {
        double planned = calculateForPortions(portions);
        if (planned == 0) {return false;}
        double deviation = Math.abs(actual - planned) / planned * 100;
        return deviation <= normDeviationPercent;
    }
}
