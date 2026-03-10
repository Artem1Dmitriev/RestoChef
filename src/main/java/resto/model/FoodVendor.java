package resto.model;

import java.util.Arrays;

public class FoodVendor {
    private String id;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;
    private IngredientCategory[] supplyCategories;
    private int deliveryDays;
    private double minOrderAmount;
    private boolean isActive;

    public FoodVendor(String id, String companyName, String contactPerson, 
                     String phone, String email, IngredientCategory[] supplyCategories) {
        this.id = id;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        // TODO: занятие 1 - валидация email (формат)
        this.email = email;
        this.supplyCategories = supplyCategories;
        this.deliveryDays = 1;
        this.minOrderAmount = 0.0;
        this.isActive = true;
    }

    // Геттеры/сеттеры...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public IngredientCategory[] getSupplyCategories() { return supplyCategories; }
    public void setSupplyCategories(IngredientCategory[] supplyCategories) { this.supplyCategories = supplyCategories; }
    public int getDeliveryDays() { return deliveryDays; }
    public void setDeliveryDays(int deliveryDays) { this.deliveryDays = deliveryDays; }
    public double getMinOrderAmount() { return minOrderAmount; }
    public void setMinOrderAmount(double minOrderAmount) { this.minOrderAmount = minOrderAmount; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат
        return "FoodVendor[" + id + "] " + companyName + " (" + String.join(", ", Arrays.toString(supplyCategories)) + ")";
    }
}
