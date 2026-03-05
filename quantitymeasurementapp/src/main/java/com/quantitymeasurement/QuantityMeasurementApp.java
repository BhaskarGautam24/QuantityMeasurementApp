package com.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length feet = new Length(1.0, LengthUnit.FEET);

        Length inches = feet.convertTo(LengthUnit.INCHES);

        System.out.println(
                "Input: Quantity(1.0, FEET).convertTo(INCHES) → Output: "
                        + inches
        );

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inch = new Length(36.0, LengthUnit.INCHES);

        System.out.println(
                "Input: Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS)) → Output: "
                        + inch.equals(yard)
        );
    }
}