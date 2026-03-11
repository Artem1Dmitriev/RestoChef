package resto.component;

//на возможное будущее
import org.springframework.stereotype.Component;
import resto.model.Unit;

@Component
public class UnitConversion {
    public double convertUnit(double amount, String from, String to) {
        Unit fromUnit = getUnit(from);
        Unit toUnit = getUnit(to);
        if (fromUnit.getType() != toUnit.getType()) {
            throw new IllegalArgumentException("Как-то не логично: " + fromUnit.getType() + " и " + toUnit.getType());
        }
        double ratio = fromUnit.getBase() / toUnit.getBase();
        return amount * ratio;
    }
    public Unit getUnit(String name) {
        name = name.toLowerCase().trim();
        return switch (name) {
            case "г", "g" -> Unit.G;
            case "кг", "kg" -> Unit.KG;
            case "л", "l" -> Unit.L;
            case "мл", "ml" -> Unit.ML;
            case "шт", "pcs" -> Unit.PIECE;
            default -> throw new IllegalArgumentException("Таких не знаем: " + name);
        };
    }
}
