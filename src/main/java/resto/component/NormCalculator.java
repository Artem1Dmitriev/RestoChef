//package resto.component;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import resto.exception.RecipeNotFoundException;
//import resto.model.KitchenOrder;
//import resto.model.Recipe;
//import resto.model.RecipeLine;
//import resto.service.RecipeService;
//
//import java.util.Map;
//
//@Component
//@RequiredArgsConstructor
//public class NormCalculator {
//    private final RecipeService recipeService;
//
//    public double calculateDeviation(KitchenOrder order, String ingredientId) throws RecipeNotFoundException {
//        Double actual = order.getActualConsumption().get(ingredientId);
//        if (actual == null) {
//            return 0.0;
//        }
//        RecipeLine line = recipeService.findRecipeLine(order.getRecipeCode(), ingredientId);
//        double planned = line.calculateForPortions(order.getPortions());
//        if (planned == 0.0) {
//            return 0.0;
//        }
//        return Math.abs(actual - planned) / planned * 100.0;
//    }
//
//    public boolean hasViolations(KitchenOrder order) throws RecipeNotFoundException {
//        for (Map.Entry<String, Double> entry : order.getActualConsumption().entrySet()) {
//            String ingredientId = entry.getKey();
//            Double actual = entry.getValue();
//            if (actual == null) continue;
//
//            RecipeLine line = recipeService.findRecipeLine(order.getRecipeCode(), ingredientId);
//
//            double planned = line.calculateForPortions(order.getPortions());
//            if (planned == 0.0) continue;
//
//            double deviation = Math.abs(actual - planned) / planned * 100.0;
//            if (deviation > line.getNormDeviationPercent()) {
//                return true;
//            }
//        }
//        return false;
//    }
//}