package resto.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import resto.exception.RecipeNotFoundException;
import resto.service.RecipeService;

@Data
public class KitchenOrder {
    private String orderId;
    private KitchenStatus status;
    private LocalDateTime createdAt;
    private String recipeCode;
    private int portions;
    private String requestedBy;
    private String approvedBy;
    private Map<String, Double> actualConsumption;
    private LocalDateTime completedAt;

    public KitchenOrder(String orderId, String recipeCode, int portions, String requestedBy) {
        this.orderId = orderId;
        this.recipeCode = recipeCode;
        this.portions = portions;
        this.requestedBy = requestedBy;
        this.status = KitchenStatus.REQUESTED;
        this.createdAt = LocalDateTime.now();
        this.actualConsumption = new HashMap<>();
    }

    public boolean canChangeStatus(KitchenStatus newStatus) {
        // TODO: занятие 4 - делегировать в KitchenStatus.canTransitionTo(newStatus)
        return false;
    }

    public void changeStatus(KitchenStatus newStatus) {
        // TODO: занятие 4 - проверить canChangeStatus, обновить статус, залогировать
    }

    public void recordActualConsumption(String ingredientId, double actualQuantity) {
        actualConsumption.put(ingredientId, actualQuantity);
    }

    public double calculateNormDeviation(String ingredientId) {
        Recipe recipe = RecipeService.getRecipe(recipeCode);
        Double actual = actualConsumption.get(ingredientId);
        if (actual == null) {
            // не должно быть так.
            return 0.0;
        }
        RecipeLine line = recipe.getLines().stream()
                .filter(l -> l.getIngredient().getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ингредиент " + ingredientId + " не найден в рецепте " + recipeCode));

        double planned = line.calculateForPortions(portions);
        if (planned == 0.0) {
            return 0.0;
        }
        double deviation = Math.abs(actual - planned) / planned * 100.0;
        return deviation;
    }

    public boolean hasNormViolation() {
        Recipe recipe = RecipeService.getRecipe(recipeCode);

        for (Map.Entry<String, Double> entry : actualConsumption.entrySet()) {
            String ingredientId = entry.getKey();
            Double actual = entry.getValue();
            if (actual == null) continue;

            RecipeLine line = recipe.getLines().stream()
                    .filter(l -> l.getIngredient().getId().equals(ingredientId))
                    .findFirst()
                    .orElse(null);
            if (line == null) {
                System.err.printf("Ошибка. Ингредиент %s не найден в рецепте %s%n",
                        ingredientId, recipeCode);
                continue;
            }

            double planned = line.calculateForPortions(portions);
            if (planned == 0) continue;

            double deviation = Math.abs(actual - planned) / planned * 100.0;
            if (deviation > line.getNormDeviationPercent()) {
                return true;
            }
        }
        return false;
    }
}
