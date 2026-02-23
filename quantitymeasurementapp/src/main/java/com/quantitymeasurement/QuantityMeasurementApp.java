package com.quantitymeasurement;
public class QuantityMeasurementApp{
    public enum LengthUnit{
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
        private static final double EPSILON=1e-6;
        public Length(double value, LengthUnit unit){
            if (!Double.isFinite(value)){
                throw new IllegalArgumentException("Value must be finite number");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }
        public double getValue(){
            return value;
        }
        public LengthUnit getUnit(){
            return unit;
        }
        private double convertToBaseUnit(){
            return value*unit.getConversionFactor();
        }
        public Length convertTo(LengthUnit targetUnit){
            if (targetUnit==null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseValue=convertToBaseUnit();
            double convertedValue=baseValue/targetUnit.getConversionFactor();
            return new Length(convertedValue,targetUnit);
        }
        @Override
        public boolean equals(Object obj){
            if (this==obj) return true;
            if (obj==null||getClass()!=obj.getClass())
                return false;
            Length other=(Length) obj;

            double thisBase=this.convertToBaseUnit();
            double otherBase=other.convertToBaseUnit();

            return Math.abs(thisBase-otherBase)<EPSILON;
        }
        @Override
        public String toString(){
            return String.format("%.6f %s",value,unit);
        }
    }
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target){

        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if(source==null||target==null){
            throw new IllegalArgumentException("Units cannot be null");
        }
        double baseValue=value*source.getConversionFactor();
        double result=baseValue/target.getConversionFactor();
        return result;
    }
    public static Length demonstrateLengthConversion(double value,LengthUnit fromUnit,LengthUnit toUnit){
        double result=convert(value, fromUnit, toUnit);
        return new Length(result, toUnit);
    }
    public static Length demonstrateLengthConversion(
            Length length,
            LengthUnit toUnit){
        return length.convertTo(toUnit);
    }
    public static boolean demonstrateLengthEquality(
            Length l1, Length l2){
        return l1.equals(l2);
    }
    public static void main(String[] args){
        System.out.println("convert(1.0, FEET, INCHES) -> "
                + convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println("convert(3.0, YARDS, FEET) -> "
                + convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println("convert(36.0, INCHES, YARDS) -> "
                + convert(36.0, LengthUnit.INCHES, LengthUnit.YARDS));
        System.out.println("convert(1.0, CENTIMETERS, INCHES) -> "
                + convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
    }
}