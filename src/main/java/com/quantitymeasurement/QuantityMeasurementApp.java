package com.quantitymeasurement;

import controller.QuantityMeasurementController;
import dto.QuantityDTO;
import repository.QuantityMeasurementCacheRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        // Create Repository (Singleton)
        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository.getInstance();
        // Create Service
        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);
        // Create Controller
        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);
        System.out.println("--- Equality Demonstration ---");
        // Create first QuantityDTO
        QuantityDTO quantity1 = new QuantityDTO(2,QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());
        // Create second QuantityDTO
        QuantityDTO quantity2 = new QuantityDTO(
                24,
                QuantityDTO.LengthUnit.INCHES.getUnitName(),
                QuantityDTO.LengthUnit.INCHES.getMeasurementType()
        );
        // Call controller
        boolean comparisonResult =
                controller.performComparison(quantity1, quantity2);

        // Display Output
        System.out.println("Operation: COMPARISON");
        System.out.println("This Quantity: " + quantity1);
        System.out.println("That Quantity: " + quantity2);
        System.out.println("Comparison Result: " + comparisonResult);
    }
}