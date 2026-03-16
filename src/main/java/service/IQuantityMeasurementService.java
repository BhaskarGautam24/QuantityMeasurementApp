package service;

import dto.QuantityDTO;

public interface IQuantityMeasurementService{

    boolean compareEquality(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO convert(QuantityDTO from,QuantityDTO to);

    QuantityDTO add(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2);

    double divide(QuantityDTO q1,QuantityDTO q2);
}
