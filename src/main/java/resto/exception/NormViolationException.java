package resto.exception;

public class NormViolationException extends Exception {
    private final String ingredientId;
    private final double planned;
    private final double actual;
    private final double deviation;

    public NormViolationException(String ingredientId, double planned, 
                                 double actual, double deviation) {
        super(String.format("Превышение нормы для %s: план %.2f, факт %.2f, отклонение %.1f%%", 
                ingredientId, planned, actual, deviation));
        this.ingredientId = ingredientId;
        this.planned = planned;
        this.actual = actual;
        this.deviation = deviation;
    }

    public String getIngredientId() { return ingredientId; }
    public double getPlanned() { return planned; }
    public double getActual() { return actual; }
    public double getDeviation() { return deviation; }
}
