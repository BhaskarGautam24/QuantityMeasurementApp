package com.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest{
    private static final double EPSILON=1e-3;

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0,LengthUnit.FEET.getConversionFactor(),EPSILON);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant(){
        assertEquals(1.0/12.0,LengthUnit.INCHES.getConversionFactor(),EPSILON);
    }
    @Test
    public void testConvertToBaseUnit_FeetToFeet(){
        assertEquals(5.0,LengthUnit.FEET.convertToBaseUnit(5.0),EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }
    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }
    @Test
    public void testQuantityLengthRefactored_Equality() {

        Length f = new Length(1.0, LengthUnit.FEET);
        Length i = new Length(12.0, LengthUnit.INCHES);

        assertTrue(f.equals(i));
    }

}