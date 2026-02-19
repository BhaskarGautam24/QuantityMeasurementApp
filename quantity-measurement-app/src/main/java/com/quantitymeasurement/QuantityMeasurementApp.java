package com.quantitymeasurement;
public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(12.0),       // 1 foot = 12 inches
        INCHES(1.0);  
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
            if (this.getClass()!=obj.getClass()) {
                return false;
            }
            Length other=(Length) obj;
            double thisBase=this.convertToBaseUnit();
            double otherBase=other.convertToBaseUnit();

            return Double.compare(thisBase,otherBase) == 0;
        }
    }
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }
    public static void demonstrateFeetEquality() {
        Length l1 = new Length(1.0,LengthUnit.FEET);
        Length l2 = new Length(1.0,LengthUnit.FEET);

        System.out.println("Input: Quantity(1.0, FEET) and Quantity(1.0, FEET)");
        System.out.println("Output: Equal (" + l1.equals(l2) + ")");
    }
    public static void demonstrateInchesEquality() {

        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        System.out.println("Input: Quantity(1.0, INCHES) and Quantity(1.0, INCHES)");
        System.out.println("Output: Equal (" + l1.equals(l2) + ")");
    }
    public static void demonstrateFeetInchesComparison() {

        Length l1 = new Length(1.0,LengthUnit.FEET);
        Length l2 = new Length(12.0,LengthUnit.INCHES);

        System.out.println("Input: Quantity(1.0, FEET) and Quantity(12.0, INCHES)");
        System.out.println("Output: Equal (" + l1.equals(l2) + ")");
    }
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}
