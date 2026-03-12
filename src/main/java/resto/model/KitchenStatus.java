package resto.model;

public enum KitchenStatus {
    REQUESTED,           // запрошен
    APPROVED,            // утверждён шефом
    PICKED,              // собрано со склада
    DELIVERED_TO_KITCHEN,// передано на кухню
    ACCEPTED,            // принято поваром
    PREPARING,           // в приготовлении
    READY,               // готово
    SERVED;              // подано

    public boolean canTransitionTo(KitchenStatus newStatus) {
        if (newStatus == null) return false;
        return switch (this) {
            case REQUESTED -> newStatus == APPROVED;
            case APPROVED -> newStatus == PICKED;
            case PICKED -> newStatus == DELIVERED_TO_KITCHEN;
            case DELIVERED_TO_KITCHEN -> newStatus == ACCEPTED;
            case ACCEPTED -> newStatus == PREPARING;
            case PREPARING -> newStatus == READY;
            case READY -> newStatus == SERVED;
            case SERVED -> false;
        };
    }

    public boolean isTerminal() {
        return this == SERVED;
    }

    public boolean requiresChefAction() {
        return this == APPROVED || this == ACCEPTED || this == READY;
    }
}
