package resto.exception;

public class InsufficientStockException extends Exception {
    private final String ingredientId;
    private final double required;
    private final double available;

    public InsufficientStockException(String ingredientId, double required, double available) {
        super(String.format("Недостаточно %s: требуется %.2f, доступно %.2f", 
                ingredientId, required, available));
        this.ingredientId = ingredientId;
        this.required = required;
        this.available = available;
    }

    public String getIngredientId() { return ingredientId; }
    public double getRequired() { return required; }
    public double getAvailable() { return available; }
}
