package com.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends InterfaceMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        double baseSum =
                unit.convertToBaseUnit(value) +
                other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(baseSum);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double baseSum =
                unit.convertToBaseUnit(value) +
                other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(baseSum);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(base, unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}