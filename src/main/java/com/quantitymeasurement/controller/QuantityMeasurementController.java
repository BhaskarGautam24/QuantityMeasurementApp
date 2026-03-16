package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {

        boolean result = service.compare(q1, q2);

        System.out.println("Comparison Result: " + result);
    }

    public void performConversion(QuantityDTO input, String targetUnit) {

        QuantityDTO result = service.convert(input, targetUnit);

        System.out.println("Converted Value: " + result.getValue() + " " + result.getUnit());
    }
}