package resto.exception;

public class IngridientNotFoundException extends Exception {
    private final String ingredientId;

    public IngridientNotFoundException(String ingredientId) {
        super("Ингредиент не найден: " + ingredientId);
        this.ingredientId = ingredientId;
    }

    public String getIngredientId() { return ingredientId; }
}
