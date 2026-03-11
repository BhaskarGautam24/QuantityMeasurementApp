package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;
    @Test
    public void testSubtraction_SameUnit() {

        Quantity<LengthUnit> result =new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> result =new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_NegativeResult() {

        Quantity<LengthUnit> result =new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ZeroResult() {

        Quantity<LengthUnit> result =new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testDivision_SameUnit() {

        double result =new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit() {

        double result =new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testDivision_ByZero() {

        assertThrows(ArithmeticException.class, () ->new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    public void testSubtraction_NullOperand() {

        Quantity<LengthUnit> q =new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,() -> q.subtract(null));
    }
}