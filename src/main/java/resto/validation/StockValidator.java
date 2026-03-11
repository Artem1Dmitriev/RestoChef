package resto.validation;

import resto.exception.*;
import resto.model.IngredientCategory;
import resto.model.StockPosition;
import resto.model.StorageZone;
import org.springframework.stereotype.Component;
@Component
public class StockValidator {

    public void validateExpiry(StockPosition position) throws ExpiredIngredientException {
        // TODO: занятие 4 - проверить что !position.isExpired()
        // TODO: бросить ExpiredIngredientException с указанием ingredientId и expiryDate
    }

    public void validateTemperature(IngredientCategory category, StorageZone zone) 
            throws TemperatureViolationException {
        // TODO: занятие 4 - проверить zone.isTemperatureSafe(category)
        // TODO: бросить TemperatureViolationException с указанием текущей температуры
    }
}
