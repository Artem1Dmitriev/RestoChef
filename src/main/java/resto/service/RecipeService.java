package resto.service;

import resto.exception.*;
import resto.logger.Logger;
import resto.model.*;

import java.util.*;

public class RecipeService {
    private final Map<String, Recipe> recipes;
    private final InventoryService inventoryService;
    private final Logger logger;

    public RecipeService(InventoryService inventoryService, Logger logger) {
        this.recipes = new HashMap<>();
        this.inventoryService = inventoryService;
        this.logger = logger;
    }

    public Recipe createRecipe(String code, String name, DishCategory category) {
        // TODO: занятие 5 - создать Recipe, сохранить в recipes, залогировать
        return null;
    }

    public void addIngredientToRecipe(String recipeCode, String ingredientId, 
                                     double quantity, String unit) 
            throws RecipeNotFoundException {
        // TODO: занятие 5 - найти рецепт и ингредиент, создать RecipeLine, добавить
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

    public boolean canPrepare(String recipeCode, int portions) 
            throws RecipeNotFoundException {
        // TODO: занятие 5 - найти рецепт, проверить достаточность остатков
        return false;
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

    public Recipe findRecipeByCode(String recipeCode) {
        Recipe recipe = recipes.get(recipeCode);
        if (recipe == null) {
            throw new RecipeNotFoundException(recipeCode);
        }
        return recipe;
    }
}
