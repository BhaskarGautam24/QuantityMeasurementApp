package com.quantitymeasurement.app.units;

import com.quantitymeasurement.app.core.IMeasurable;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double toBaseUnit(double value) { return value * conversionFactor; }
    public double fromBaseUnit(double value) { return value / conversionFactor; }

    public static VolumeUnit fromString(String unit) {
        return VolumeUnit.valueOf(unit.trim().toUpperCase());
    }
}
