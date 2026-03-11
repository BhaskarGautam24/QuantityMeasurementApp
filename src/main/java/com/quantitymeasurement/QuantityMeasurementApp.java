package com.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES)));

        System.out.println(new Quantity<>(10.0, WeightUnit.KILOGRAM).subtract(new Quantity<>(5000.0, WeightUnit.GRAM)));

        System.out.println(new Quantity<>(5.0, VolumeUnit.LITRE).subtract(new Quantity<>(500.0, VolumeUnit.MILLILITRE)));

        System.out.println(new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET)));

        System.out.println(new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET)));

        System.out.println(new Quantity<>(10.0, WeightUnit.KILOGRAM).divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        System.out.println(new Quantity<>(5.0, VolumeUnit.LITRE).divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}