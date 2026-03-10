package resto.exception;

import java.time.LocalDate;

public class ExpiredIngredientException extends Exception {
    private final String ingredientId;
    private final LocalDate expiryDate;

    public ExpiredIngredientException(String ingredientId, LocalDate expiryDate) {
        super(String.format("Ингредиент %s просрочен: срок годности %s", 
                ingredientId, expiryDate));
        this.ingredientId = ingredientId;
        this.expiryDate = expiryDate;
    }

    public String getIngredientId() { return ingredientId; }
    public LocalDate getExpiryDate() { return expiryDate; }
}
