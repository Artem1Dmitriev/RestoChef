package resto.service;

import resto.component.UnitConversion;
import resto.exception.*;
import resto.logger.Logger;
import resto.model.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final Map<String, Recipe> recipes;
    private final UnitConversion unitConversion;
    private final InventoryService inventoryService;
    private final Logger logger;

    public RecipeService(UnitConversion unitConversion, InventoryService inventoryService, Logger logger) {
        this.unitConversion = unitConversion;
        this.recipes = new HashMap<>();
        this.inventoryService = inventoryService;
        this.logger = logger;
    }

    public Recipe createRecipe(String code, String name, DishCategory category) {
        Recipe recipe = new Recipe(code,name,category);
        recipes.put(code, recipe);
        logger.log(String.format("[CATALOG] Добавлен рецепт: %s", name));
        return recipe;
    }

    public void addIngredientToRecipe(String recipeCode, String ingredientId,
                                      double quantity, String unit) throws RecipeNotFoundException, IngridientNotFoundException {
        Recipe recipe = findRecipeByCode(recipeCode);
        Ingredient ingredient = inventoryService.getIngredient(ingredientId);
        double converted = unitConversion.convertUnit(quantity, unit, ingredient.getUnit());
        RecipeLine line = new RecipeLine(ingredient, converted);
        recipe.addRecipeLine(line);
        logger.log(String.format("[CATALOG] Добавлен ингредиент %s в рецепт %s", ingredient.getName(), recipeCode));
    }

    public double calculateFoodCost(String recipeCode, int portions) 
            throws RecipeNotFoundException {
        // TODO: занятие 5 - найти рецепт, вызвать calculateFoodCost(portions)
        return 0;
    }

    public Map<String, Double> getRecipeRequirements(String recipeCode, int portions) 
            throws RecipeNotFoundException {
        // TODO: занятие 5 - найти рецепт, вызвать calculateRequirements(portions)
        return new HashMap<>();
    }

    public List<Recipe> getRecipesByCategory(DishCategory category) {
        // TODO: занятие 5 - фильтрация по категории
        return new ArrayList<>();
    }

    public Recipe cloneRecipe(String sourceCode, String newCode) 
            throws RecipeNotFoundException {
        // TODO: занятие 5 - найти исходный рецепт, создать копию с новым кодом, сохранить
        return null;
    }

    public Recipe findRecipeByCode(String recipeCode) throws RecipeNotFoundException {
        Recipe recipe = recipes.get(recipeCode);
        if (recipe == null) {
            throw new RecipeNotFoundException(recipeCode);
        }
        return recipe;
    }
    // Вынесем из Recipe, т.к. это обязанность сервиса
    public boolean canPrepare(String recipeCode, int portions) throws RecipeNotFoundException {
        Recipe recipe = findRecipeByCode(recipeCode);
        Map<String, Double> requirements = recipe.calculateRequirements(portions);
        for (Map.Entry<String, Double> entry : requirements.entrySet()) {
            if (inventoryService.getAvailableStock(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
    // KitchenOrder -> NormCalculator -> сюда, вдруг будем переиспользовать
    public RecipeLine findRecipeLine(String recipeCode, String ingredientId) throws RecipeNotFoundException, IngridientNotFoundInRecipeException {
        Recipe recipe = findRecipeByCode(recipeCode);
        return recipe.getLines().stream()
                .filter(line -> line.getIngredient().getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngridientNotFoundInRecipeException(ingredientId,recipeCode));
    }
}
