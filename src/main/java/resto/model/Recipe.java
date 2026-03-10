package resto.model;

import resto.recipe.CostCalculable;
import resto.service.InventoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // TODO: занятие 1 - добавить метод addIngredient(Ingredient ingredient, double quantity, String unit)

    @Override
    public double calculateFoodCost(int portions) {
        // TODO: занятие 1 - суммировать calculateCostForPortions(portions) по всем lines
        return 0;
    }

    public Map<String, Double> calculateRequirements(int portions) {
        // TODO: занятие 1 - создать Map<ingredientId, calculateForPortions(portions)>
        return new HashMap<>();
    }

    public boolean canPrepare(int portions, InventoryService inventory) {
        // TODO: занятие 1 - для каждого ингредиента проверить что inventory.getAvailableStock() >= требуемому
        return false;
    }

    // Геттеры/сеттеры...
    public String getRecipeCode() { return recipeCode; }
    public void setRecipeCode(String recipeCode) { this.recipeCode = recipeCode; }
    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }
    public DishCategory getDishCategory() { return dishCategory; }
    public void setDishCategory(DishCategory dishCategory) { this.dishCategory = dishCategory; }
    public List<RecipeLine> getLines() { return lines; }
    public int getPreparationTimeMinutes() { return preparationTimeMinutes; }
    public void setPreparationTimeMinutes(int preparationTimeMinutes) { this.preparationTimeMinutes = preparationTimeMinutes; }
    public String getChefNotes() { return chefNotes; }
    public void setChefNotes(String chefNotes) { this.chefNotes = chefNotes; }

    @Override
    public String toString() {
        // TODO: занятие 1 - сделать читаемый формат
        return "Recipe[" + recipeCode + "] " + dishName + " (" + dishCategory + ")";
    }
}
