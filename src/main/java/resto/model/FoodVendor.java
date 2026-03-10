package resto.model;

import java.util.Arrays;
import java.util.regex.Pattern;

@Data
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
        setPhone(phone);
        setEmail(email);
        this.supplyCategories = supplyCategories;
        this.deliveryDays = 1;
        this.minOrderAmount = 0.0;
        this.isActive = true;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер телефона пуст");
        }
        String cleaned = phone.replaceAll("[^0-9+]", "");
        boolean hasPlus = cleaned.startsWith("+");
        if (hasPlus) {
            cleaned = cleaned.substring(1);
        }
        if (cleaned.startsWith("8")) {
            cleaned = "7" + cleaned.substring(1);
        }
        if (cleaned.length() != 11 || !cleaned.startsWith("7")) {
            throw new IllegalArgumentException("+7 или 8 + 10 цифр");
        }
        this.phone = "+7" + cleaned.substring(1);
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email пуст");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Неверный формат email");
        }
        this.email = email;
    }
}
