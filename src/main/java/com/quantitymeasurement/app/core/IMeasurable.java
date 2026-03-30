package com.quantitymeasurement.app.core;

public interface IMeasurable {
    double toBaseUnit(double value);
    double fromBaseUnit(double value);
}
