package com.quantitymeasurement;

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        System.out.println("Equality Comparisons:\n");
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v4 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v5 = new Quantity<>(0.264172, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v6 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v7 = new Quantity<>(0.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v8 = new Quantity<>(3.78541, VolumeUnit.LITRE);

        System.out.println("Input: new Quantity<>(1.0, LITRE).equals(new Quantity<>(1.0, LITRE)) → Output: "+v1.equals(v2));
        System.out.println("Input: new Quantity<>(1.0, LITRE).equals(new Quantity<>(1000.0, MILLILITRE)) → Output: "+v1.equals(v3));
        System.out.println("Input: new Quantity<>(1.0, GALLON).equals(new Quantity<>(1.0, GALLON)) → Output: "+v4.equals(v4));

        System.out.println("Input: new Quantity<>(1.0, LITRE).equals(new Quantity<>(~0.264172, GALLON)) → Output: "+v1.equals(v5));

        System.out.println("Input: new Quantity<>(500.0, MILLILITRE).equals(new Quantity<>(0.5, LITRE)) → Output: "+v6.equals(v7));

        System.out.println("Input: new Quantity<>(3.78541, LITRE).equals(new Quantity<>(1.0, GALLON)) → Output: "+v8.equals(v4));


        System.out.println("\nUnit Conversions:\n");

        System.out.println("Input: new Quantity<>(1.0, LITRE).convertTo(MILLILITRE) → Output: "+v1.convertTo(VolumeUnit.MILLILITRE));

        Quantity<VolumeUnit> g2 = new Quantity<>(2.0, VolumeUnit.GALLON);
        System.out.println("Input: new Quantity<>(2.0, GALLON).convertTo(LITRE) → Output: "+g2.convertTo(VolumeUnit.LITRE));

        System.out.println("Input: new Quantity<>(500.0, MILLILITRE).convertTo(GALLON) → Output: "+v6.convertTo(VolumeUnit.GALLON));

        Quantity<VolumeUnit> zero = new Quantity<>(0.0, VolumeUnit.LITRE);
        System.out.println("Input: new Quantity<>(0.0, LITRE).convertTo(MILLILITRE) → Output: "+zero.convertTo(VolumeUnit.MILLILITRE));

        System.out.println("Input: new Quantity<>(1.0, LITRE).convertTo(LITRE) → Output: "+v1.convertTo(VolumeUnit.LITRE));


        System.out.println("\nAddition Operations (Implicit Target Unit):\n");

        Quantity<VolumeUnit> a1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> a2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        System.out.println("Input: new Quantity<>(1.0, LITRE).add(new Quantity<>(2.0, LITRE)) → Output: "+a1.add(a2));

        System.out.println("Input: new Quantity<>(1.0, LITRE).add(new Quantity<>(1000.0, MILLILITRE)) → Output: "+a1.add(v3));

        System.out.println("Input: new Quantity<>(500.0, MILLILITRE).add(new Quantity<>(0.5, LITRE)) → Output: "+v6.add(v7));

        Quantity<VolumeUnit> gAdd = new Quantity<>(2.0, VolumeUnit.GALLON);
        System.out.println("Input: new Quantity<>(2.0, GALLON).add(new Quantity<>(3.78541, LITRE)) → Output: "+gAdd.add(v8));


        System.out.println("\nAddition Operations (Explicit Target Unit):\n");

        System.out.println("Input: new Quantity<>(1.0, LITRE).add(new Quantity<>(1000.0, MILLILITRE), MILLILITRE) → Output: "+v1.add(v3, VolumeUnit.MILLILITRE));

        System.out.println("Input: new Quantity<>(1.0, GALLON).add(new Quantity<>(3.78541, LITRE), GALLON) → Output: "+v4.add(v8, VolumeUnit.GALLON));

        System.out.println("Input: new Quantity<>(500.0, MILLILITRE).add(new Quantity<>(1.0, LITRE), GALLON) → Output: "+v6.add(v1, VolumeUnit.GALLON));

        Quantity<VolumeUnit> l2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> g4 = new Quantity<>(4.0, VolumeUnit.GALLON);

        System.out.println("Input: new Quantity<>(2.0, LITRE).add(new Quantity<>(4.0, GALLON), LITRE) → Output: "+l2.add(g4, VolumeUnit.LITRE));


        System.out.println("\nCategory Incompatibility:\n");

        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println("Input: new Quantity<>(1.0, LITRE).equals(new Quantity<>(1.0, FOOT)) → Output: "+v1.equals(length));

        System.out.println("Input: new Quantity<>(1.0, LITRE).equals(new Quantity<>(1.0, KILOGRAM)) → Output: "+v1.equals(weight));
    }
}