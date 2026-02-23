package com.quantitymeasurement;

public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private final double conversionFactor;
       LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }
public static class Length{
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;
        public Length(double value, LengthUnit unit){
            if(!Double.isFinite(value)){
                throw new IllegalArgumentException("Value must be finite number");
            }
           if (unit==null){
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value=value;
            this.unit=unit;
        }
        public double getValue(){
            return value;
        }
        public LengthUnit getUnit(){
            return unit;
        }
        
        private double convertToBaseUnit(){
            return value * unit.getConversionFactor();
        }
        public Length add(Length other, LengthUnit targetUnit){
            if (other==null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            if (targetUnit == null){
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double thisBase=this.convertToBaseUnit();
            double otherBase=other.convertToBaseUnit();

            double sumBase=thisBase+otherBase;

            double resultValue=sumBase/targetUnit.getConversionFactor();

            return new Length(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            double thisBase = this.convertToBaseUnit();
            double otherBase = other.convertToBaseUnit();

            return Math.abs(thisBase - otherBase) < EPSILON;
        }
        @Override
        public String toString() {
            return String.format("Quantity(%.3f, %s)", value, unit);
        }
    }
    public static void main(String[] args){

        Length f1 = new Length(1.0,LengthUnit.FEET);
        Length i1 = new Length(12.0,LengthUnit.INCHES);
        System.out.println("Input: add(Quantity(1.0, FEET), Quantity(12.0, INCHES), FEET) → Output: "
                        + f1.add(i1,LengthUnit.FEET));
        System.out.println("Input: add(Quantity(1.0, FEET), Quantity(12.0, INCHES), INCHES) → Output: "
                        + f1.add(i1,LengthUnit.INCHES));
        System.out.println("Input: add(Quantity(1.0, FEET), Quantity(12.0, INCHES), YARDS) → Output: "
                        + f1.add(i1,LengthUnit.YARDS));
        Length y1 = new Length(1.0,LengthUnit.YARDS);
        Length f2 = new Length(3.0,LengthUnit.FEET);

        System.out.println("Input: add(Quantity(1.0, YARDS), Quantity(3.0, FEET), YARDS) → Output: "
                        + y1.add(f2,LengthUnit.YARDS));
    }
}