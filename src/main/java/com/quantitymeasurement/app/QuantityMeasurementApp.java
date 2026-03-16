package com.quantitymeasurement.app;

import com.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.quantitymeasurement.service.IQuantityMeasurementService;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.quantitymeasurement.app.util.ApplicationConfig;
import com.quantitymeasurement.dto.QuantityDTO;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        try {

            // Load MySQL driver
            Class.forName(ApplicationConfig.getProperty("db.driver"));

        } catch (Exception e) {
            throw new RuntimeException("Database Driver Not Found", e);
        }

        IQuantityMeasurementRepository repository;

        String repoType =
                ApplicationConfig.getProperty("app.repository.type");

        if ("database".equalsIgnoreCase(repoType)) {

            repository = new QuantityMeasurementDatabaseRepository();

            // IMPORTANT
            repository.initializeDatabase();

            System.out.println("Using Database Repository (MySQL)");

        } else {

            repository = QuantityMeasurementCacheRepository.getInstance();
            System.out.println("Using Cache Repository");
        }

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        System.out.println("Quantity Measurement Application Started");

        // TEST OPERATION (THIS WILL INSERT INTO DB)

        QuantityDTO q1 = new QuantityDTO(2, "FEET");
        QuantityDTO q2 = new QuantityDTO(24, "INCHES");

        boolean result = service.compare(q1, q2);

        System.out.println("Comparison Result: " + result);

    }
}