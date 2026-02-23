package com.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.quantitymeasurement.QuantityMeasurementApp.Length;
import com.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest{
    private static final double EPSILON=1e-6;
    @Test
    public void testConversion_FeetToInches(){
        assertEquals(12.0,
                QuantityMeasurementApp.convert(
                        1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),EPSILON);
    }
    @Test
    public void testConversion_InchesToFeet(){
        assertEquals(2.0,
                QuantityMeasurementApp.convert(
                        24.0,
                        LengthUnit.INCHES,
                        LengthUnit.FEET),EPSILON);
    }

    @Test
    public void testConversion_YardsToInches(){
        assertEquals(36.0,
                QuantityMeasurementApp.convert(
                        1.0,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_ZeroValue(){
        assertEquals(0.0,
                QuantityMeasurementApp.convert(
                        0.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_NegativeValue(){
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(
                        -1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_InvalidUnit_Throws(){
        assertThrows(IllegalArgumentException.class, ()->{ 
            QuantityMeasurementApp.convert(1.0,null,LengthUnit.FEET);
        });
    }
    @Test
    public void testConversion_NaN_Throws(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.convert(
                    Double.NaN,
                    LengthUnit.FEET,
                    LengthUnit.INCHES);
        });
    }
    @Test
    public void testEquality_YardToFeet(){

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testRoundTripConversion(){
        double value = 5.0;
        double feet =QuantityMeasurementApp.convert(value,LengthUnit.YARDS,LengthUnit.FEET);

        double yards =QuantityMeasurementApp.convert(feet,LengthUnit.FEET,LengthUnit.YARDS);
        assertEquals(value, yards, EPSILON);
    }
}