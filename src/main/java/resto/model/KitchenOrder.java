package resto.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
        // TODO: занятие 1 - добавить/обновить запись в actualConsumption
    }

    public double calculateNormDeviation(String ingredientId) {
        // TODO: занятие 1 - рассчитать % отклонения факта от нормы для ингредиента
        // TODO: требуется доступ к Recipe для получения нормы
        return 0;
    }

    public boolean hasNormViolation() {
        // TODO: занятие 1 - проверить есть ли превышение нормы > 5% по любому ингредиенту
        return false;
    }

    // Геттеры/сеттеры...
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public KitchenStatus getStatus() { return status; }
    public void setStatus(KitchenStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getRecipeCode() { return recipeCode; }
    public void setRecipeCode(String recipeCode) { this.recipeCode = recipeCode; }
    public int getPortions() { return portions; }
    public void setPortions(int portions) { this.portions = portions; }
    public String getRequestedBy() { return requestedBy; }
    public void setRequestedBy(String requestedBy) { this.requestedBy = requestedBy; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public Map<String, Double> getActualConsumption() { return actualConsumption; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    @Override
    public String toString() {
        // TODO: занятие 1 - сделать читаемый формат
        return "KitchenOrder[" + orderId + "] " + recipeCode + " x" + portions + " - " + status;
    }
}
