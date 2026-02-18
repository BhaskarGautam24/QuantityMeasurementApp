package com.feetmeasurementequality;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetMeasurementTest {

    @Test
    public void testFeetEquality_SameValue() {

        FeetMeasurement.Feet first =
                new FeetMeasurement.Feet(1.0);

        FeetMeasurement.Feet second =
                new FeetMeasurement.Feet(1.0);

        assertTrue(first.equals(second));
    }

    @Test
    public void testFeetEquality_DifferentValue() {

        FeetMeasurement.Feet first =
                new FeetMeasurement.Feet(1.0);

        FeetMeasurement.Feet second =
                new FeetMeasurement.Feet(2.0);

        assertFalse(first.equals(second));
    }

    @Test
    public void testFeetEquality_NullComparison() {

        FeetMeasurement.Feet first =
                new FeetMeasurement.Feet(1.0);

        assertFalse(first.equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {

        FeetMeasurement.Feet first =
                new FeetMeasurement.Feet(1.0);

        String other = "1.0";

        assertFalse(first.equals(other));
    }

    @Test
    public void testFeetEquality_SameReference() {

        FeetMeasurement.Feet first =
                new FeetMeasurement.Feet(1.0);

        assertTrue(first.equals(first));
    }
}
