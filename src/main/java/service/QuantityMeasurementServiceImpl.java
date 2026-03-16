package service;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import exception.DatabaseException;
import repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // ----------------------------
    // Convert units to base value
    // ----------------------------
    private double convertToBase(QuantityDTO quantity) {

        String unit = quantity.getUnit();
        double value = quantity.getValue();

        switch (unit) {

            // LENGTH (base = INCHES)

            case "FEET":
                return value * 12;

            case "INCHES":
                return value;

            case "YARD":
                return value * 36;

            case "CENTIMETER":
                return value * 0.393701;

            // WEIGHT (base = GRAM)

            case "KILOGRAM":
                return value * 1000;

            case "GRAM":
                return value;

            case "TONNE":
                return value * 1000000;

            // VOLUME (base = MILLILITRE)

            case "LITRE":
                return value * 1000;

            case "MILLILITRE":
                return value;

            case "GALLON":
                return value * 3785.41;

            // TEMPERATURE (base = CELSIUS)

            case "FAHRENHEIT":
                return (value - 32) * 5 / 9;

            case "CELSIUS":
                return value;

            case "KELVIN":
                return value - 273.15;

            default:
                throw new DatabaseException("Unsupported Unit: " + unit,null);
        }
    }

    // ----------------------------
    // Equality comparison
    // ----------------------------
    @Override
    public boolean compareEquality(QuantityDTO q1, QuantityDTO q2) {

        // Validate measurement type
        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new DatabaseException(
                    "Cannot compare different measurement types",null);
        }

        double value1 = convertToBase(q1);
        double value2 = convertToBase(q2);

        boolean result = Math.abs(value1 - value2) < 0.0001;

        // Save result in repository
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "COMPARISON",
                        q1.toString(),
                        q2.toString(),
                        String.valueOf(result)
                );

        repository.save(entity);

        return result;
    }

    // ----------------------------
    // Convert operation
    // ----------------------------
    @Override
    public QuantityDTO convert(QuantityDTO from, QuantityDTO to) {

        double baseValue = convertToBase(from);

        return new QuantityDTO(baseValue, to.getUnit(), to.getMeasurementType());
    }

    // ----------------------------
    // Addition
    // ----------------------------
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new DatabaseException(
                    "Cannot add different measurement types",null);
        }

        double value1 = convertToBase(q1);
        double value2 = convertToBase(q2);

        double result = value1 + value2;

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurementType());
    }

    // ----------------------------
    // Subtraction
    // ----------------------------
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new DatabaseException(
                    "Cannot subtract different measurement types",null);
        }

        double value1 = convertToBase(q1);
        double value2 = convertToBase(q2);

        double result = value1 - value2;

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurementType());
    }

    // ----------------------------
    // Division
    // ----------------------------
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        double value1 = convertToBase(q1);
        double value2 = convertToBase(q2);

        if (value2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return value1 / value2;
    }
}