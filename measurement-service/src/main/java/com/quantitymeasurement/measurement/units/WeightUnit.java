package com.quantitymeasurement.measurement.units;

import com.quantitymeasurement.measurement.core.IMeasurable;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    MILLIGRAM(0.000001),
    POUND(0.453592),
    OUNCE(0.0283495),
    TON(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double toBaseUnit(double value) { return value * conversionFactor; }
    public double fromBaseUnit(double value) { return value / conversionFactor; }

    public static WeightUnit fromString(String unit) {
        return WeightUnit.valueOf(unit.trim().toUpperCase());
    }
}
