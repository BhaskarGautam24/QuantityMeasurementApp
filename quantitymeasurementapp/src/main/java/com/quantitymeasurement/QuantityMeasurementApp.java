package com.quantitymeasurement;
public class QuantityMeasurementApp{
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
        private static final double EPSILON=1e-6;
        public Length(double value, LengthUnit unit){
            if (!Double.isFinite(value)){
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
        public Length convertTo(LengthUnit targetUnit){
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseValue = convertToBaseUnit();
            double convertedValue = baseValue / targetUnit.getConversionFactor();
            return new Length(convertedValue, targetUnit);
        }
        
        
        public Length add(Length other){
            if (other==null) {
                throw new IllegalArgumentException("Other length cannot be null");
            }
            double thisBase=this.convertToBaseUnit();
            double otherBase=other.convertToBaseUnit();
            double sumBase=thisBase+otherBase;
            double resultValue=sumBase/this.unit.getConversionFactor();
            return new Length(resultValue,this.unit);
        }
        @Override
        public boolean equals(Object obj) {
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
            return String.format("%.6f %s", value, unit);
        }
    }
    public static double convert(double value,LengthUnit source,LengthUnit target){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (source==null||target==null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        double baseValue=value*source.getConversionFactor();
        return baseValue/target.getConversionFactor();
    }
    public static Length add(double value1,LengthUnit unit1,double value2,LengthUnit unit2) {
        Length l1=new Length(value1,unit1);
        Length l2=new Length(value2,unit2);
        return l1.add(l2);
    }
    public static void main(String[] args){
        Length l1=new Length(1.0,LengthUnit.FEET);
        Length l2=new Length(12.0,LengthUnit.INCHES);
        Length result=l1.add(l2);
        System.out.println("1 FEET + 12 INCHES = "+result);
  
        Length f1=new Length(1.0,LengthUnit.FEET);
        Length f2=new Length(2.0,LengthUnit.FEET);
        System.out.println("1 FEET + 2 FEET = "+f1.add(f2));
        
        Length y1=new Length(1.0,LengthUnit.YARDS);
        Length f3=new Length(3.0,LengthUnit.FEET);
        System.out.println("1 YARD + 3 FEET = "+y1.add(f3));
        
        Length small1=new Length(0.001,LengthUnit.FEET);
        Length small2=new Length(0.002,LengthUnit.FEET);
        System.out.println("Small Addition = "+small1.add(small2));
    }
}