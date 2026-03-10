package resto.model;

import resto.recipe.CostCalculable;
import resto.service.InventoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Recipe implements CostCalculable {
    private String recipeCode;
    private String dishName;
    private DishCategory dishCategory;
    private List<RecipeLine> lines;
    private int preparationTimeMinutes;
    private String chefNotes;

    public Recipe(String recipeCode, String dishName, DishCategory category) {
        this.recipeCode = recipeCode;
        this.dishName = dishName;
        this.dishCategory = category;
        this.lines = new ArrayList<>();
        this.preparationTimeMinutes = 0;
        this.chefNotes = "";
    }
    publice void addIngredient(Ingredient ingredient, double quantity, String unit) {
        RecipeLine line = new RecipeLine(ingredient,quantity,unit);
        lines.add(line);
    }

    @Override
    public double calculateFoodCost(int portions) {
        if (portions <= 0) return 0.0;
        double totalCost =  0.0;
        for (Recipe line : lines) {
            totalCost += line.calculatCostForPortions(portions);
        }
        return totalCost;
    }

    public Map<String, Double> calculateRequirements(int portions) {
        Map<String, Double> requirements = new HashMap<>();
        if (portions <= 0) return requirements;

        for (RecipeLine line : lines) {
            String ingredientId = line.getIngredient().getId();
            double required = line.calculateForPortions(portions);
            // либо новый либо добавить к старому
            requirements.merge(ingredientId, required, Double::sum);
        }
        return requirements;
    }

    public boolean canPrepare(int portions, InventoryService inventory) {
        if (portions <= 0) return true;

        Map<String, Double> requirements = calculateRequirements(portions);
        for (Map.Entry<String, Double> entry : requirements.entrySet()) {
            String ingredientId = entry.getKey();
            double needed = entry.getValue();
            double available = inventory.getAvailableStock(ingredientId);
            if (available < needed) {
                return false;
            }
        }
        return true;
    }
}
