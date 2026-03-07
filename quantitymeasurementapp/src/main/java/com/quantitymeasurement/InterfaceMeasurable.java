package com.quantitymeasurement;

public interface InterfaceMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}