package resto.model;

import lombok.Data;
import resto.component.UnitConversion;

// TODO: убрать unit. Приравнивать будем в RecipeService.addIngridientToRecipe
@Data
public class RecipeLine {
    private String id;
    private Ingredient ingredient;
    private double quantityPerPortion;
    private String unit;
    private double normDeviationPercent;

    public RecipeLine(Ingredient ingredient, double quantityPerPortion, String unit) {
        this.id = ingredient.getId() + "-line";
        this.ingredient = ingredient;
        setUnit(unit, quantityPerPortion);
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

    private void setUnit(String inputUnit, double inputQuantity) {
        String baseUnit = ingredient.getUnit();
        if (inputUnit.equalsIgnoreCase(baseUnit)) {
            this.quantityPerPortion = inputQuantity;
        } else {
            UnitConversion converter = new UnitConversion();
            this.quantityPerPortion = converter.convertUnit(inputQuantity, inputUnit, baseUnit);
        }
        this.unit = baseUnit;
    }
}
