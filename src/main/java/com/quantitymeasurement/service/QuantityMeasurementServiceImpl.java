package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.core.Quantity;
import com.quantitymeasurement.units.LengthUnit;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        LengthUnit unit1 = LengthUnit.valueOf(q1.getUnit());
        LengthUnit unit2 = LengthUnit.valueOf(q2.getUnit());

        Quantity quantity1 = new Quantity(q1.getValue(), unit1);
        Quantity quantity2 = new Quantity(q2.getValue(), unit2);

        boolean result = quantity1.equals(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1.getValue() + " " + q1.getUnit(),
                q2.getValue() + " " + q2.getUnit(),
                String.valueOf(result)
        ));

        return result;
    }

    public QuantityDTO convert(QuantityDTO input, String targetUnit) {
        LengthUnit unit = LengthUnit.valueOf(input.getUnit());
        LengthUnit target = LengthUnit.valueOf(targetUnit);

        Quantity quantity = new Quantity(input.getValue(), unit);
        Quantity result = quantity.convertTo(target);

        QuantityDTO output = new QuantityDTO(result.getValue(), targetUnit);

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                input.getValue() + " " + input.getUnit(),
                targetUnit,
                output.getValue() + " " + output.getUnit()
        ));

        return output;
    }

    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        LengthUnit unit1 = LengthUnit.valueOf(q1.getUnit());
        LengthUnit unit2 = LengthUnit.valueOf(q2.getUnit());

        Quantity quantity1 = new Quantity(q1.getValue(), unit1);
        Quantity quantity2 = new Quantity(q2.getValue(), unit2);

        Quantity result = quantity1.add(quantity2);
        QuantityDTO output = new QuantityDTO(result.getValue(), result.getUnit().toString());

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                q1.getValue() + " " + q1.getUnit(),
                q2.getValue() + " " + q2.getUnit(),
                output.getValue() + " " + output.getUnit()
        ));

        return output;
    }

    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        LengthUnit unit1 = LengthUnit.valueOf(q1.getUnit());
        LengthUnit unit2 = LengthUnit.valueOf(q2.getUnit());

        Quantity quantity1 = new Quantity(q1.getValue(), unit1);
        Quantity quantity2 = new Quantity(q2.getValue(), unit2);

        Quantity result = quantity1.subtract(quantity2);
        QuantityDTO output = new QuantityDTO(result.getValue(), result.getUnit().toString());

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1.getValue() + " " + q1.getUnit(),
                q2.getValue() + " " + q2.getUnit(),
                output.getValue() + " " + output.getUnit()
        ));

        return output;
    }

    public double divide(QuantityDTO q1, QuantityDTO q2) {
        LengthUnit unit1 = LengthUnit.valueOf(q1.getUnit());
        LengthUnit unit2 = LengthUnit.valueOf(q2.getUnit());

        Quantity quantity1 = new Quantity(q1.getValue(), unit1);
        Quantity quantity2 = new Quantity(q2.getValue(), unit2);

        double result = quantity1.divide(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1.getValue() + " " + q1.getUnit(),
                q2.getValue() + " " + q2.getUnit(),
                String.valueOf(result)
        ));

        return result;
    }
}