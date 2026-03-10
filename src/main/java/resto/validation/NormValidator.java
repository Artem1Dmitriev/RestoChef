package resto.validation;

import resto.exception.NormViolationException;

public class NormValidator {

    public void validateConsumption(String ingredientId, double planned, double actual) 
            throws NormViolationException {
        // TODO: занятие 4 - проверить отклонение не более 5% (по умолчанию)
        // TODO: бросить NormViolationException с деталями если превышено
    }

    public void validatePortionSize(int requested, int maxAllowed) throws IllegalArgumentException {
        // TODO: занятие 4 - проверить что requested <= maxAllowed (например, 100 порций максимум)
    }
}
