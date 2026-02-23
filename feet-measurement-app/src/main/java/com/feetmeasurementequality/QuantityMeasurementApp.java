package com.feetmeasurementequality;

public class QuantityMeasurementApp{
    public static class Feet{
        private final double value;
        public Feet(double value) {

            if (!Double.isFinite(value)){
                throw new IllegalArgumentException("Value must be a valid numeric value");
            }

            this.value = value;
        }
        public double getValue(){
            return value;
        }
        @Override
        public boolean equals(Object obj){
            if (this==obj){
                return true;
            }
            if (obj==null){
                return false;
            }
            if (getClass()!=obj.getClass()){
                return false;
            }
            Feet other=(Feet) obj;
            return Double.compare(this.value, other.value)==0;
        }
        @Override
        public int hashCode(){
            return Double.hashCode(value);
        }

        @Override
        public String toString(){
            return value + " ft";
        }
    }
    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        Feet f3 = new Feet(2.0);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + f1.equals(f2) + ")");
        System.out.println();
        System.out.println("Input: 1.0 ft and 2.0 ft");
        System.out.println("Output: Equal (" + f1.equals(f3) + ")");
        System.out.println();
        System.out.println("Input: 1.0 ft and null");
        System.out.println("Output: Equal (" + f1.equals(null) + ")");
    }
}