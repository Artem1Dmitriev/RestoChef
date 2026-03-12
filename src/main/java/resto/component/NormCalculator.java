package resto.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import resto.exception.IngridientNotFoundInRecipeException;
import resto.exception.RecipeNotFoundException;
import resto.model.KitchenOrder;
import resto.model.RecipeLine;
import resto.service.RecipeService;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NormCalculator {
    private final RecipeService recipeService;

    public record DeviationInfo (double planned, double actual, double allowedPercent) {
        public double deviation() {
            if (planned == 0.0) return 0.0;
            return Math.abs(actual - planned) / planned * 100.0;
        }
        boolean isExceeded() {
            return deviation() > allowedPercent;
        }
    }

    public DeviationInfo getDeviationInfo(KitchenOrder order, String ingredientId)
            throws RecipeNotFoundException, IngridientNotFoundInRecipeException {
        Double actual = order.getActualConsumption().get(ingredientId);
        if (actual == null) {
            return new DeviationInfo(0.0, 0.0, 0.0);
        }
        RecipeLine line = recipeService.findRecipeLine(order.getRecipeCode(), ingredientId);
        double planned = line.calculateForPortions(order.getPortions());
        return new DeviationInfo(planned, actual, line.getNormDeviationPercent());
    }

    public double calculateDeviation(KitchenOrder order, String ingredientId) throws RecipeNotFoundException, IngridientNotFoundInRecipeException {
        return getDeviationInfo(order, ingredientId).deviation();
    }

    public boolean hasViolations(KitchenOrder order) throws RecipeNotFoundException, IngridientNotFoundInRecipeException {
        for (String ingredientId : order.getActualConsumption().keySet()) {
            if (getDeviationInfo(order, ingredientId).isExceeded()) {
                return true;
            }
        }
        return false;
    }
    public Map<String, Double> getViolations(KitchenOrder order)
            throws RecipeNotFoundException, IngridientNotFoundInRecipeException {
        Map<String, Double> violations = new HashMap<>();
        for (String ingredientId : order.getActualConsumption().keySet()) {
            DeviationInfo info = getDeviationInfo(order, ingredientId);
            if (info.isExceeded()) {
                violations.put(ingredientId, info.actual());
            }
        }
        return violations;
    }
}