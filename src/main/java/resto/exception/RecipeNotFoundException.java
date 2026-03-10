package resto.exception;

public class RecipeNotFoundException extends Exception {
    private final String recipeCode;

    public RecipeNotFoundException(String recipeCode) {
        super("Рецепт не найден: " + recipeCode);
        this.recipeCode = recipeCode;
    }

    public String getRecipeCode() { return recipeCode; }
}
