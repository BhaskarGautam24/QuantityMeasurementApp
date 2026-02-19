package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.quantitymeasurement.QuantityMeasurementApp.Length;
import com.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {

        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {

        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_FeetDifferentValue() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_InchDifferentValue() {

        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_SameReference() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l1));
    }

    @Test
    public void testEquality_NullComparison() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    @Test
    public void testEquality_NullUnit() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }
}
