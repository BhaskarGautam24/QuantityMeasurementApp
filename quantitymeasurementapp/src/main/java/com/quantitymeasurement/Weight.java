package com.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-6;

    public Weight(double value, WeightUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = convertToBaseUnit();
        double result = targetUnit.convertFromBaseUnit(base);

        return new Weight(result, targetUnit);
    }

    public Weight add(Weight other) {

        if (other == null)
            throw new IllegalArgumentException("Other weight cannot be null");

        double sumBase = this.convertToBaseUnit() + other.convertToBaseUnit();
        double result = unit.convertFromBaseUnit(sumBase);

        return new Weight(result, unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase = this.convertToBaseUnit() + other.convertToBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Weight other = (Weight) obj;

        double thisBase = this.convertToBaseUnit();
        double otherBase = other.convertToBaseUnit();

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.3f, %s)", value, unit);
    }
}