package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;
    @Test
    public void lengthFeetEqualsInches(){

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void lengthYardsEqualsFeet(){

        Quantity<LengthUnit> yards =
                new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> feet =
                new Quantity<>(3.0, LengthUnit.FEET);

        assertTrue(yards.equals(feet));
    }

    @Test
    public void weightKilogramEqualsGrams(){

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> g =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }

    @Test
    public void weightPoundEqualsGrams(){

        Quantity<WeightUnit> pound =
                new Quantity<>(1.0, WeightUnit.POUND);

        Quantity<WeightUnit> grams =
                new Quantity<>(453.592, WeightUnit.GRAM);

        assertTrue(pound.equals(grams));
    }

    @Test
    public void convertLengthFeetToInches(){

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    public void convertWeightKilogramsToGrams(){

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void addLengthFeetAndInches(){

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void addWeightKilogramsAndGrams(){

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(grams, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testGenericTypeSafetyWithWeight(){

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void preventCrossTypeComparisonLengthVsWeight(){

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    @Test
    public void preventCrossTypeAdditionLengthVsWeight(){

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
}