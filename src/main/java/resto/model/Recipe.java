package resto.model;

import resto.recipe.CostCalculable;

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
    public void addRecipeLine(RecipeLine line) {
        lines.add(line);
    }

    @Override
    public double calculateFoodCost(int portions) {
        if (portions <= 0) return 0.0;
        double totalCost =  0.0;
        for (RecipeLine line : lines) {
            totalCost += line.calculateCostForPortions(portions);
        }
        return totalCost;
    }

    public Map<String, Double> calculateRequirements(int portions) {
        Map<String, Double> requirements = new HashMap<>();
        if (portions <= 0) return requirements;

        for (RecipeLine line : lines) {
            String ingredientId = line.getIngredient().getId();
            double required = line.calculateForPortions(portions);
            // либо новый, либо добавить к старому
            requirements.merge(ingredientId, required, Double::sum);
        }
        return requirements;
    }
}
