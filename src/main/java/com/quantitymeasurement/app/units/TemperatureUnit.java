package com.quantitymeasurement.app.units;

import com.quantitymeasurement.app.core.IMeasurable;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS, FAHRENHEIT, KELVIN;

    public double toBaseUnit(double value) {
        switch (this) {
            case CELSIUS: return value + 273.15;
            case FAHRENHEIT: return (value - 32) * 5.0 / 9.0 + 273.15;
            case KELVIN: return value;
        }
        return value;
    }

    public double fromBaseUnit(double value) {
        switch (this) {
            case CELSIUS: return value - 273.15;
            case FAHRENHEIT: return (value - 273.15) * 9.0 / 5.0 + 32;
            case KELVIN: return value;
        }
        return value;
    }

    public static TemperatureUnit fromString(String unit) {
        return TemperatureUnit.valueOf(unit.trim().toUpperCase());
    }
}
