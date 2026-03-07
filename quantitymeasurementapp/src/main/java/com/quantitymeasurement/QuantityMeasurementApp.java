package com.quantitymeasurement;
public class QuantityMeasurementApp {
    public static void main(String[] args) {

        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(1.0, KILOGRAM)) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1.0, WeightUnit.KILOGRAM)));

        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM)) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1000.0, WeightUnit.GRAM)));

        System.out.println("Input: Quantity(2.0, POUND).equals(Quantity(2.0, POUND)) → Output: "
                + new Weight(2.0, WeightUnit.POUND)
                .equals(new Weight(2.0, WeightUnit.POUND)));

        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(2.20462, POUND)) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(2.20462, WeightUnit.POUND)));

        System.out.println("Input: Quantity(500.0, GRAM).equals(Quantity(0.5, KILOGRAM)) → Output: "
                + new Weight(500.0, WeightUnit.GRAM)
                .equals(new Weight(0.5, WeightUnit.KILOGRAM)));

        System.out.println("Input: Quantity(1.0, POUND).equals(Quantity(453.592, GRAM)) → Output: "
                + new Weight(1.0, WeightUnit.POUND)
                .equals(new Weight(453.592, WeightUnit.GRAM)));


        System.out.println("Input: Quantity(1.0, KILOGRAM).convertTo(GRAM) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));

        System.out.println("Input: Quantity(2.0, POUND).convertTo(KILOGRAM) → Output: "
                + new Weight(2.0, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM));

        System.out.println("Input: Quantity(500.0, GRAM).convertTo(POUND) → Output: "
                + new Weight(500.0, WeightUnit.GRAM).convertTo(WeightUnit.POUND));

        System.out.println("Input: Quantity(0.0, KILOGRAM).convertTo(GRAM) → Output: "
                + new Weight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));

        System.out.println("\n===== Addition Operations (Implicit Target Unit) =====");

        System.out.println("Input: Quantity(1.0, KILOGRAM).add(Quantity(2.0, KILOGRAM)) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM)));

        System.out.println("Input: Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM)) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM)));

        System.out.println("Input: Quantity(500.0, GRAM).add(Quantity(0.5, KILOGRAM)) → Output: "
                + new Weight(500.0, WeightUnit.GRAM)
                .add(new Weight(0.5, WeightUnit.KILOGRAM)));


        System.out.println("Input: Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM));

        System.out.println("Input: Quantity(1.0, POUND).add(Quantity(453.592, GRAM), POUND) → Output: "
                + new Weight(1.0, WeightUnit.POUND)
                .add(new Weight(453.592, WeightUnit.GRAM), WeightUnit.POUND));

        System.out.println("Input: Quantity(2.0, KILOGRAM).add(Quantity(4.0, POUND), KILOGRAM) → Output: "
                + new Weight(2.0, WeightUnit.KILOGRAM)
                .add(new Weight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM));

        

        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(1.0, FEET)) → Output: "
                + new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }
}