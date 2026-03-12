package resto.exception;

public class IngridientNotFoundInRecipeException extends Exception {
    private final String ingredientId;
    private final String recipeCode;

    public IngridientNotFoundInRecipeException(String ingredientId, String recipeCode) {
        super("Ингредиент " + ingredientId + " не найден в рецепте " + recipeCode);
        this.ingredientId = ingredientId;
        this.recipeCode = recipeCode;
    }

    public String getIngredientId() { return ingredientId; }
    public String getRecipeCode() { return recipeCode; }
}
