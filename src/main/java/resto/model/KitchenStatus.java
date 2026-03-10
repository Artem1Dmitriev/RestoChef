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
        // TODO: занятие 4 - реализовать логику переходов:
        // REQUESTED → APPROVED → PICKED → DELIVERED_TO_KITCHEN → ACCEPTED → PREPARING → READY → SERVED
        return false;
    }
    
    // TODO: занятие 3 - добавить метод isTerminal() для SERVED
    // TODO: занятие 3 - добавить метод requiresChefAction() для APPROVED, ACCEPTED, READY
}
