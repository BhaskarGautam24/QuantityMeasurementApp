 package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.quantitymeasurement.QuantityMeasurementApp.Length;
import com.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-3;
    @Test
    public void testAddition_TargetFeet(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    @Test
    public void testAddition_TargetInches(){
        Length l1 = new Length(1.0,LengthUnit.FEET);
        Length l2 = new Length(12.0,LengthUnit.INCHES);
        Length result = l1.add(l2,LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(),EPSILON);
        assertEquals(LengthUnit.INCHES,result.getUnit());
    }
    @Test
    public void testAddition_TargetYards(){

        Length l1 = new Length(1.0,LengthUnit.FEET);
        Length l2 = new Length(12.0,LengthUnit.INCHES);
        Length result = l1.add(l2,LengthUnit.YARDS);
        assertEquals(0.667, result.getValue(),EPSILON);
        assertEquals(LengthUnit.YARDS,result.getUnit());
    }

    @Test
    public void testAddition_TargetCentimeters() {

        Length l1 = new Length(1.0,LengthUnit.INCHES);
        Length l2 = new Length(1.0,LengthUnit.INCHES);
        Length result = l1.add(l2,LengthUnit.CENTIMETERS);
        assertEquals(5.08, result.getValue(), 1e-2);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAddition_TargetSameAsFirstOperand(){

        Length yard = new Length(1.0,LengthUnit.YARDS);
        Length feet = new Length(3.0,LengthUnit.FEET);
        Length result = yard.add(feet,LengthUnit.YARDS);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_TargetSameAsSecondOperand(){

        Length yard = new Length(1.0,LengthUnit.YARDS);
        Length feet = new Length(3.0,LengthUnit.FEET);
        Length result = yard.add(feet,LengthUnit.FEET);
        assertEquals(6.0, result.getValue(),EPSILON);
    }

    @Test
    public void testAddition_Commutativity_WithTarget(){

        Length l1 = new Length(1.0,LengthUnit.FEET);
        Length l2 = new Length(12.0,LengthUnit.INCHES);
        Length r1 = l1.add(l2,LengthUnit.YARDS);
        Length r2 = l2.add(l1,LengthUnit.YARDS);
        assertTrue(r1.equals(r2));
    }

    @Test
    public void testAddition_WithZero_TargetYards(){

        Length l1 = new Length(5.0,LengthUnit.FEET);
        Length zero = new Length(0.0,LengthUnit.INCHES);
        Length result = l1.add(zero,LengthUnit.YARDS);
        assertEquals(1.667, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues_TargetInches(){

        Length l1 = new Length(5.0,LengthUnit.FEET);
        Length l2 = new Length(-2.0,LengthUnit.FEET);

        Length result = l1.add(l2,LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NullTargetUnit(){

        Length l1 = new Length(1.0,LengthUnit.FEET);
        Length l2 = new Length(12.0,LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class,() -> {
            l1.add(l2, null);
        });
    }
}