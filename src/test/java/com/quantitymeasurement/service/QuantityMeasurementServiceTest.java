package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementServiceTest {

    private static IQuantityMeasurementRepository repository;
    private static IQuantityMeasurementService service;

    // ⚡ Toggle DB or cache
    private static boolean useDatabase = true;

    @BeforeAll
    static void setup() {
        repository = useDatabase ? new QuantityMeasurementDatabaseRepository()
                                 : QuantityMeasurementCacheRepository.getInstance();

        if (useDatabase) {
            repository.initializeDatabase(); // create table if not exists
        }

        //repository.deleteAll(); // clear previous data
        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    void testAddOperation() {
        QuantityDTO q1 = new QuantityDTO(10, "FEET");
        QuantityDTO q2 = new QuantityDTO(5, "FEET");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(15, result.getValue(), 0.001);
        assertEquals("FEET", result.getUnit());

        List<QuantityMeasurementEntity> data = repository.getAllMeasurements();
        assertEquals("ADD", data.get(data.size() - 1).getOperation()); // last record
    }

    @Test
    void testSubtractOperation() {
        QuantityDTO q1 = new QuantityDTO(10, "FEET");
        QuantityDTO q2 = new QuantityDTO(5, "FEET");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(5, result.getValue(), 0.001);

        List<QuantityMeasurementEntity> data = repository.getAllMeasurements();
        assertEquals("SUBTRACT", data.get(data.size() - 1).getOperation());
    }

    @Test
    void testCompareOperationEqual() {
        QuantityDTO q1 = new QuantityDTO(24, "INCHES");
        QuantityDTO q2 = new QuantityDTO(2, "FEET");

        boolean result = service.compare(q1, q2);
        assertTrue(result);

        List<QuantityMeasurementEntity> data = repository.getAllMeasurements();
        assertEquals("COMPARE", data.get(data.size() - 1).getOperation());
    }

    @Test
    void testConvertOperation() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");

        QuantityDTO result = service.convert(q1, "INCHES");

        assertEquals(12, result.getValue(), 0.001);
        assertEquals("INCHES", result.getUnit());

        List<QuantityMeasurementEntity> data = repository.getAllMeasurements();
        assertEquals("CONVERT", data.get(data.size() - 1).getOperation());
    }

    @Test
    void testDivideOperation() {
        QuantityDTO q1 = new QuantityDTO(10, "FEET");
        QuantityDTO q2 = new QuantityDTO(5, "FEET");

        double result = service.divide(q1, q2);
        assertEquals(2, result, 0.001);

        List<QuantityMeasurementEntity> data = repository.getAllMeasurements();
        assertEquals("DIVIDE", data.get(data.size() - 1).getOperation());
    }
}