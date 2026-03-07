package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest{

    private static final double EPSILON = 1e-3;
    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue(){
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(g.equals(kg));
    }
    @Test
    public void testEquality_WeightVsLength_Incompatible(){
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Length foot = new Length(1.0, LengthUnit.FEET);
        assertFalse(kg.equals(foot));
    }

    @Test
    public void testEquality_NullComparison(){
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertFalse(kg.equals(null));
    }
    @Test
    public void testEquality_SameReference(){
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(kg.equals(kg));
    }

    @Test
    public void testEquality_NullUnit(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(1.0, null);
        });
    }
    @Test
    public void testEquality_NegativeWeight() {
        Weight kg = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(-1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    public void testEquality_LargeWeightValue() {
        Weight g = new Weight(1000000.0, WeightUnit.GRAM);
        Weight kg = new Weight(1000.0, WeightUnit.KILOGRAM);
        assertTrue(g.equals(kg));
    }

    @Test
    public void testEquality_SmallWeightValue() {
        Weight kg = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight g = new Weight(1.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }
    @Test
    public void testConversion_PoundToKilogram() {
        Weight lb = new Weight(2.20462, WeightUnit.POUND);
        Weight result = lb.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_KilogramToPound() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), EPSILON);
    }
    @Test
    public void testConversion_ZeroValue() {
        Weight kg = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(2.0, WeightUnit.KILOGRAM);

        Weight result = a.add(b);

        assertEquals(3.0, result.getValue(), EPSILON);
    }
}