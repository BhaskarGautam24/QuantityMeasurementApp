package com.quantitymeasurement;

import controller.QuantityMeasurementController;
import entity.QuantityMeasurementEntity;
import repository.*;
import dto.QuantityDTO;
import service.*;
import util.ApplicationConfig;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        String repoType = ApplicationConfig.getProperty("repository.type");

        IQuantityMeasurementRepository repository;

        if ("database".equalsIgnoreCase(repoType)) {
            repository = new QuantityMeasurementDatabaseRepository();
            System.out.println("Using Database Repository");
        } else {
            repository = QuantityMeasurementCacheRepository.getInstance();
            System.out.println("Using Cache Repository");
        }

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        System.out.println("\n--- Equality Demonstration ---");

        QuantityDTO quantity1 =
                new QuantityDTO(2, "FEET", "Length");

        QuantityDTO quantity2 =
                new QuantityDTO(24, "INCHES", "Length");

        boolean result =
                controller.performComparison(quantity1, quantity2);

        System.out.println("Operation: COMPARISON");
        System.out.println("This Quantity: " + quantity1);
        System.out.println("That Quantity: " + quantity2);
        System.out.println("Comparison Result: " + result);
    }
}