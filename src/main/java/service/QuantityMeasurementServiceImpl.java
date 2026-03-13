package service;

import dto.QuantityDTO;
import exception.QuantityMeasurementException;
import repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compareEquality(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new QuantityMeasurementException("Cross category comparison not allowed");
        }

        return Math.abs(q1.getValue() - q2.getValue()) < 0.0001;
    }

    @Override
    public QuantityDTO convert(QuantityDTO from, QuantityDTO to) {

        return new QuantityDTO(from.getValue(), to.getUnit(), to.getMeasurementType());
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot add different measurement categories");
        }

        double result = q1.getValue() + q2.getValue();

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurementType());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() - q2.getValue();

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurementType());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        if (q2.getValue() == 0) {
            throw new QuantityMeasurementException("Division by zero");
        }

        return q1.getValue() / q2.getValue();
    }
}