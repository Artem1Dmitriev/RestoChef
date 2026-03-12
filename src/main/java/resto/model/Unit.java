package resto.model;

public enum Unit {
    G(UnitType.MASS, 1.0),
    KG(UnitType.MASS, 1000.0),
    L(UnitType.VOL, 1000.0),
    ML(UnitType.VOL, 1.0),

    PIECE(UnitType.COUNT, 1.0);

    private final UnitType type;
    private final double base;

    public UnitType getType() {return type;}
    public double getBase() {return base;}

    Unit(UnitType type, double base) {
        this.type = type;
        this.base = base;
    }

    public enum UnitType {
        MASS, VOL, COUNT
    }
}