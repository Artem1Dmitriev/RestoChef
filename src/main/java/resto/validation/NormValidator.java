package resto.validation;

import lombok.RequiredArgsConstructor;
import resto.component.NormCalculator;
import resto.exception.IngridientNotFoundInRecipeException;
import resto.exception.NormViolationException;
import org.springframework.stereotype.Component;
import resto.exception.RecipeNotFoundException;
import resto.model.KitchenOrder;

@Component
@RequiredArgsConstructor
public class NormValidator {
    private final NormCalculator normCalculator;

    public void validateConsumption(KitchenOrder order, String ingredientId) throws NormViolationException, RecipeNotFoundException, IngridientNotFoundInRecipeException {
        NormCalculator.DeviationInfo info = normCalculator.getDeviationInfo(order, ingredientId);
        if (info.deviation() > info.allowedPercent()) {
            throw new NormViolationException(ingredientId, info.planned(), info.actual(), info.deviation());
        }
    }

    public void validatePortionSize(int requested, int maxAllowed) throws IllegalArgumentException {
        // TODO: занятие 4 - проверить что requested <= maxAllowed (например, 100 порций максимум)
    }
}
