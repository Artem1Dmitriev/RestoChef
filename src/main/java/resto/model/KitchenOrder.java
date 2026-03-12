package resto.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;


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

    public void recordActualConsumption(String ingredientId, double actualQuantity) {
        actualConsumption.put(ingredientId, actualQuantity);
    }
}
