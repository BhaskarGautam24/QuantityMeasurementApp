package com.quantitymeasurement.app.core;

public class Quantity<T extends IMeasurable> {

    private double value;
    private T unit;

    public Quantity(double value, T unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public T getUnit() { return unit; }

    public double toBase() {
        return unit.toBaseUnit(value);
    }

    public Quantity<T> convertTo(T targetUnit) {
        double base = unit.toBaseUnit(value);
        double converted = targetUnit.fromBaseUnit(base);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<T> add(Quantity<T> other) {
        double base = this.toBase() + other.toBase();
        double result = unit.fromBaseUnit(base);
        return new Quantity<>(result, unit);
    }

    public double divide(Quantity<T> other) {
        return this.toBase() / other.toBase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity)) return false;
        Quantity<?> other = (Quantity<?>) obj;
        return Math.abs(this.toBase() - other.toBase()) < 0.0001;
    }
}
