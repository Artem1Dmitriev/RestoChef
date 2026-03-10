package resto.component;

//на возможное будущее
import org.springframework.stereotype.Component;
import resto.model.Unit

@Component
public class UnitConversionService {
    public double convertUnit(double amount, String from, String to) {
        Unit fromUnit = getUnit(from);
        Unit toUnit = getUnit(to);
        if (fromUnit.getType() != toUnit.getType()) {
            throw new IllegalArgumentException("Как-то не логично: " + fromUnit.getType() + " и " + toUnit.getType());
        }
        double ratio = fromUnit.base / toUnit.base;
        return amount * ratio;
    }
    public Unit getUnit(String name) {
        name = name.toLowerCase().trim();
        switch (name) {
            case "г": case "g": return Unit.G;
            case "кг": case "kg": return Unit.KG;
            case "л": case "l": return Unit.L;
            case "мл": case "ml": return Unit.ML;
            case "шт": case "pcs": return Unit.PIECE;
            default: throw new IllegalArgumentException("Таких не знаем: " + name);
        }
    }
}
