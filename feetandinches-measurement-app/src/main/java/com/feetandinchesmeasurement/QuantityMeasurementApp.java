package com.feetandinchesmeasurement;

public class QuantityMeasurementApp {
    public static class Feet {

        private final double value;
        public Feet(double value) {

            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            this.value = value;
        }

        public double getValue() {
            return value;
        }

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

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
    public static class Inches {

        private final double value;
        public Inches(double value) {

            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            this.value = value;
        }

        public double getValue() {
            return value;
        }
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

            Inches other = (Inches) obj;

            // Floating point comparison
            return Double.compare(this.value, other.value) == 0;
        }
    }
    public static void demonstrateFeetEquality() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");

        if (first.equals(second)) {
            System.out.println("Output: Equal (true)");
        } else {
            System.out.println("Output: Not Equal (false)");
        }
    }
    public static void demonstrateInchesEquality() {

        Inches first = new Inches(1.0);
        Inches second = new Inches(1.0);

        System.out.println("Input: 1.0 inch and 1.0 inch");

        if (first.equals(second)) {
            System.out.println("Output: Equal (true)");
        } else {
            System.out.println("Output: Not Equal (false)");
        }
    }

    public static void main(String[] args) {

        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}
