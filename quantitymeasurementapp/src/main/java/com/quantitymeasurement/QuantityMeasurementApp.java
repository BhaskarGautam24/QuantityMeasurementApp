package com.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends InterfaceMeasurable> boolean demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.equals(q2);
    }

    public static <U extends InterfaceMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    public static <U extends InterfaceMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<LengthUnit>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<LengthUnit>(12.0, LengthUnit.INCHES);

        System.out.println(
                "Input: new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(12.0, LengthUnit.INCHES)) → Output: "
                        + length1.equals(length2)
        );

        System.out.println(
                "Input: new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES) → Output: "
                        + length1.convertTo(LengthUnit.INCHES)
        );

        System.out.println(
                "Input: new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET) → Output: "
                        + length1.add(length2, LengthUnit.FEET)
        );


        Quantity<WeightUnit> weight1 =
                new Quantity<WeightUnit>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<WeightUnit>(1000.0, WeightUnit.GRAM);

        System.out.println(
                "Input: new Quantity<>(1.0, WeightUnit.KILOGRAM).equals(new Quantity<>(1000.0, WeightUnit.GRAM)) → Output: "
                        + weight1.equals(weight2)
        );

        System.out.println(
                "Input: new Quantity<>(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM) → Output: "
                        + weight1.convertTo(WeightUnit.GRAM)
        );

        System.out.println(
                "Input: new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM) → Output: "
                        + weight1.add(weight2, WeightUnit.KILOGRAM)
);

        Quantity<LengthUnit> lengthExample =
                new Quantity<LengthUnit>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weightExample =
                new Quantity<WeightUnit>(1.0, WeightUnit.KILOGRAM);

        System.out.println(
                "Input: new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)) → Output: "
                        + lengthExample.equals(weightExample)
        );
    }
}