package resto.model;

import lombok.Data;
import resto.component.UnitConversionService;

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
        planned = calculateForPortions(portions);
        if (planned == 0) {return false;}
        deviation = Math.abs(actual - planned) / planned * 100
        return deviation <= normDeviationPercent;
    }

    private void setUnit(String inputUnit, double inputQuantity) {
        String baseUnit = ingredient.getUnit();
        if (inputUnit.equalsIgnoreCase(baseUnit)) {
            this.quantityPerPortion = inputQuantity;
        } else {
            //  TODO: вынести конвертацию в сервисный слой.
            UnitConversionService converter = new UnitConversionService();
            double converted = converter.convertUnit(inputQuantity, inputUnit, baseUnit);
            this.quantityPerPortion = converted;
        }
        this.unit = baseUnit;
    }
}
