package com.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.quantitymeasurement.QuantityMeasurementApp.Length;
import com.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
public class QuantityMeasurementAppTest {
    @Test
    public void testEquality_YardToFeet(){

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }
    @Test
    public void testEquality_YardToInches(){

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }
    @Test
    public void testEquality_CentimeterToInches(){

        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }
    @Test
    public void testEquality_YardDifferentValue(){
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.YARDS);

        assertFalse(l1.equals(l2));
    }
    @Test
    public void testEquality_ComplexTransitive(){
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inch = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}