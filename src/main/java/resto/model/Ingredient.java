package resto.model;

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

    // TODO: занятие 1 - добавить геттеры/сеттеры для всех полей

    public double convertUnit(double amount, String fromUnit) {
        // TODO: занятие 1 - конвертация единиц: кг ↔ г (1000), л ↔ мл (1000), шт ↔ шт (1)
        // TODO: вернуть amount в целевых единицах (unit)
        return amount;
    }

    @Override
    public String toString() {
        // TODO: занятие 1 - сделать читаемый формат через String.format()
        return "Ingredient[" + id + "] " + name + " (" + category + ")";
    }
}
