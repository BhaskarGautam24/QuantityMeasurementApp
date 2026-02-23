package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.quantitymeasurement.QuantityMeasurementApp.Length;
import com.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
public class QuantityMeasurementAppTest {
   private static final double EPSILON=1e-6;
    @Test
    public void testAddition_SameUnit_FeetPlusFeet(){
        Length l1=new Length(1.0,LengthUnit.FEET);
        Length l2=new Length(2.0,LengthUnit.FEET);
        Length result = l1.add(l2);
        assertEquals(3.0, result.getValue(),EPSILON);
        assertEquals(LengthUnit.FEET,result.getUnit());
    }
    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length l1=new Length(6.0,LengthUnit.INCHES);
        Length l2=new Length(6.0,LengthUnit.INCHES);
        Length result = l1.add(l2);
        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }
    @Test
    public void testAddition_FeetPlusInches(){
        Length l1=new Length(1.0,LengthUnit.FEET);
        Length l2=new Length(12.0,LengthUnit.INCHES);
        Length result=l1.add(l2);
        assertEquals(2.0,result.getValue(),EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    @Test
    public void testAddition_InchesPlusFeet() {
        Length l1 = new Length(12.0,LengthUnit.INCHES);
        Length l2 = new Length(1.0,LengthUnit.FEET);
        Length result=l1.add(l2);

        assertEquals(24.0,result.getValue(),EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_YardPlusFeet() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        Length result = yard.add(feet);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_CentimeterPlusInch() {

        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);

        Length result = cm.add(inch);

        assertEquals(5.08, result.getValue(), 1e-2);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }
    @Test
    public void testAddition_Commutativity() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result1 = l1.add(l2);
        Length result2 = l2.add(l1);

        assertTrue(result1.equals(result2));
    }

    @Test
    public void testAddition_WithZero() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length zero = new Length(0.0, LengthUnit.INCHES);

        Length result = l1.add(zero);

        assertEquals(5.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_NegativeValues() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_LargeValues() {
        Length l1 = new Length(1e6, LengthUnit.FEET);
        Length l2 = new Length(1e6, LengthUnit.FEET);
        Length result = l1.add(l2);
        assertEquals(2e6, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_SmallValues() {
        Length l1 = new Length(0.001, LengthUnit.FEET);
        Length l2 = new Length(0.002, LengthUnit.FEET);
        Length result = l1.add(l2);

       assertEquals(0.003, result.getValue(), EPSILON);
    }
}
