package com.quantitymeasurement.measurement.core;

public interface IMeasurable {
    double toBaseUnit(double value);
    double fromBaseUnit(double value);
}
