package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;
    @Test
    public void testEquality_LitreToLitre_SameValue(){

        Quantity<VolumeUnit> v1=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2=new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {

        Quantity<VolumeUnit> v1=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2=new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {

        Quantity<VolumeUnit> v1=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2=new Quantity<>(0.264172, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {

        Quantity<VolumeUnit> volume=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length=new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }
    @Test
    public void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> v=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result=v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> v=new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result=v.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {

        Quantity<VolumeUnit> v=new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result=v.convertTo(VolumeUnit.LITRE);

        assertEquals(5.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {

        Quantity<VolumeUnit> v1=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2=new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result=v1.add(v2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {

        Quantity<VolumeUnit> v1=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2=new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result=v1.add(v2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {

        Quantity<VolumeUnit> v1=new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2=new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result=v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_WithZero() {

        Quantity<VolumeUnit> v1=new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> zero=new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result=v1.add(zero);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testEquality_NullComparison() {

        Quantity<VolumeUnit> v=new Quantity<>(1.0, VolumeUnit.LITRE);

        assertFalse(v.equals(null));
    }
}