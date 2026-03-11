package resto.service;

import resto.exception.*;
import resto.logger.Logger;
import resto.model.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final Map<String, Ingredient> ingredientCatalog;
    private final Map<String, List<StockPosition>> stockByIngredient;
    private final Map<String, StockPosition> positionsById;
    private final Map<String, StorageZone> zones;
    private final Logger logger;

    public InventoryService(Logger logger) {
        this.ingredientCatalog = new HashMap<>();
        this.stockByIngredient = new HashMap<>();
        this.positionsById = new HashMap<>();
        this.zones = new HashMap<>();
        this.logger = logger;
    }

    // ОБРАЗЕЦ РЕАЛИЗАЦИИ (занятие 2)
    public void addIngredient(Ingredient ingredient) {
        ingredientCatalog.put(ingredient.getId(), ingredient);
        stockByIngredient.put(ingredient.getId(), new ArrayList<>());
        logger.log("[CATALOG] Добавлен ингредиент: " + ingredient.getName());
    }

    public StockPosition acceptIngredient(Ingredient ingredient, double quantity, 
                                         LocalDate expiryDate, StorageZone zone) 
            throws TemperatureViolationException, ExpiredIngredientException {
        // TODO: занятие 2 - проверить isTemperatureSafe для категории ингредиента
        // TODO: бросить TemperatureViolationException если нарушен температурный режим
        // TODO: проверить что expiryDate > today, иначе ExpiredIngredientException
        // TODO: проверить canAccept, создать StockPosition, сохранить, залогировать
        return null;
    }

    public void transferToZone(String positionId, String toZoneId, double amount) 
            throws TemperatureViolationException {
        // TODO: занятие 2 - найти позицию, проверить целевую зону на температурную совместимость
        // TODO: обновить zone, залогировать перемещение
    }

    public Map<String, Double> reserveForOrder(String recipeCode, int portions) 
            throws InsufficientStockException {
        // TODO: занятие 2 - получить Recipe, рассчитать требования
        // TODO: для каждого ингредиента найти позиции (FEFO по expiryDate), зарезервировать
        // TODO: вернуть Map<ingredientId, зарезервированное_количество>
        return new HashMap<>();
    }

    public void writeOffConsumption(String positionId, double amount, String reason) {
        // TODO: занятие 2 - найти позицию, вызвать writeOff(), залогировать списание
    }

    public List<StockPosition> getStockByIngredient(String ingredientId) {
        // TODO: занятие 2 - вернуть список позиций из stockByIngredient
        return new ArrayList<>();
    }

    public double getAvailableStock(String ingredientId) {
        // TODO: занятие 2 - суммировать getAvailable() по всем позициям ингредиента
        return 0;
    }

    public List<StockPosition> getExpiringStock(int days) {
        // TODO: занятие 2 - вернуть позиции где getDaysUntilExpiry() <= days
        return new ArrayList<>();
    }

    public List<Ingredient> getLowStockReport() {
        // TODO: занятие 5 - вернуть ингредиенты где getAvailableStock() < minStockLevel
        return new ArrayList<>();
    }

    public void addStorageZone(StorageZone zone) {
        // TODO: занятие 2 - добавить зону в zones
    }

    public StorageZone getZone(String zoneId) {
        // TODO: занятие 2 - получить зону по id
        return null;
    }

    public Ingredient getIngredient(String ingredientId) {
        // TODO: занятие 2 - получить ингредиент из каталога
        return null;
    }
}
