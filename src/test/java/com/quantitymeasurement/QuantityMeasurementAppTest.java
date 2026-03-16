package com.quantitymeasurement;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repository.IQuantityMeasurementRepository;
import service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

   
	private QuantityMeasurementServiceImpl service;

	@BeforeEach
	void setUp() {
	    service = new QuantityMeasurementServiceImpl(null);
	}

    @Test
    void givenDifferentMeasurementTypes_WhenCompared_ShouldThrowException() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1, "KILOGRAM", "WEIGHT");

        assertThrows(RuntimeException.class, () -> {
            service.compareEquality(q1, q2);
        });
    }
    @Test
    void givenTwoLengthQuantities_WhenAdded_ShouldReturnCorrectResult() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(24, result.getValue(), 0.01);
    }
    @Test
    void givenTwoLengthQuantities_WhenSubtracted_ShouldReturnCorrectResult() {

        QuantityDTO q1 = new QuantityDTO(24, "INCHES", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1, "FEET", "LENGTH");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(12, result.getValue(), 0.01);
    }
    @Test
    void givenTwoQuantities_WhenDivided_ShouldReturnCorrectRatio() {

        QuantityDTO q1 = new QuantityDTO(24, "INCHES", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        double result = service.divide(q1, q2);

        assertEquals(2.0, result);
    }
    @Test
    void givenDivisionByZero_ShouldThrowException() {

        QuantityDTO q1 = new QuantityDTO(10, "INCHES", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0, "INCHES", "LENGTH");

        assertThrows(ArithmeticException.class, () -> {
            service.divide(q1, q2);
        });
    }
}