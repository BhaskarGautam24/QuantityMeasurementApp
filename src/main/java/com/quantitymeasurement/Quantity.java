package com.quantitymeasurement;

public class Quantity<U extends InterfaceMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.01;

    public Quantity(double value, U unit) {

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

    public U getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = convertToBaseUnit();
        double result = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other cannot be null");

        double baseSum = convertToBaseUnit() + other.convertToBaseUnit();

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross category subtraction");

        double baseResult =
                convertToBaseUnit() - other.convertToBaseUnit();

        double result = targetUnit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross category division");

        double divisor = other.convertToBaseUnit();

        if (Math.abs(divisor) < EPSILON)
            throw new ArithmeticException("Division by zero");

        return convertToBaseUnit() / divisor;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = convertToBaseUnit();
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.2f, %s)", value, unit);
    }
}