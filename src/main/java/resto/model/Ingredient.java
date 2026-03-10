package resto.model;

// Задание 1 пока используем Data, которое даёт встроенные геттеры, сеттеры и toString. Позже, если будет нужно - исправим.
@Data
public class Ingredient {
    private String id;
    private String name;
    private IngredientCategory category;
    private String unit;
    private double costPerUnit;
    private String supplierId;
    private int shelfLifeDays;
    private double minStockLevel;

    public Ingredient(String id, String name, IngredientCategory category, 
                     String unit, double costPerUnit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unit = unit;
        this.costPerUnit = costPerUnit;
        this.supplierId = "";
        this.shelfLifeDays = 0;
        this.minStockLevel = 0.0;
    }
}
