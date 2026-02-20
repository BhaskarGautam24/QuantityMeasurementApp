package com.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(12.0),           // 1 foot = 12 inches
        INCHES(1.0),          // Base unit
        YARDS(36.0),          // 1 yard = 36 inches
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {

            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        // Convert everything to base unit (inches)
        private double convertToBaseUnit() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (this.getClass() != obj.getClass()) {
                return false;
            }

            Length other = (Length) obj;

            double thisBase = this.convertToBaseUnit();
            double otherBase = other.convertToBaseUnit();

            return Double.compare(thisBase, otherBase) == 0;
        }
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)");
        System.out.println("Output: Equal (" + l1.equals(l2) + ")");

        Length l3 = new Length(1.0, LengthUnit.YARDS);
        Length l4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(36.0, INCHES)");
        System.out.println("Output: Equal (" + l3.equals(l4) + ")");

        Length l5 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l6 = new Length(0.393701, LengthUnit.INCHES);
        System.out.println("Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)");
        System.out.println("Output: Equal (" + l5.equals(l6) + ")");
    }
}