package resto.model;

public enum DishCategory {
    HOT_APPETIZER(1),   // горячие закуски
    COLD_APPETIZER(1),  // холодные закуски
    SOUP(2),            // супы
    MAIN_COURSE(3),     // основные блюда
    DESSERT(4),         // десерты
    BEVERAGE(1);        // напитки

    private final double courseOrder;

    DishCategory(double courseOrder) {
        this.courseOrder = courseOrder;
    }

    public double getCourseOrder() {
        return courseOrder;
    }
}
