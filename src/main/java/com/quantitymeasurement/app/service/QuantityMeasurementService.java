package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.core.Quantity;
import com.quantitymeasurement.app.dto.QuantityInputDTO;
import com.quantitymeasurement.app.dto.ResponseDTO;
import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.app.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.app.units.LengthUnit;
import com.quantitymeasurement.app.units.TemperatureUnit;
import com.quantitymeasurement.app.units.VolumeUnit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementService(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseDTO convertQuantities(QuantityInputDTO dto, String userEmail) {
        String type = dto.getType() == null ? "LENGTH" : dto.getType().toUpperCase();
        double fromValue = dto.getThisQuantityDTO().getValue();
        String fromUnit = dto.getThisQuantityDTO().getUnit();
        String toUnit = dto.getThatQuantityDTO().getUnit();
        double result;

        switch (type) {
            case "TEMPERATURE": {
                Quantity<TemperatureUnit> q = new Quantity<>(fromValue, TemperatureUnit.fromString(fromUnit));
                result = q.convertTo(TemperatureUnit.fromString(toUnit)).getValue();
                break;
            }
            case "VOLUME": {
                Quantity<VolumeUnit> q = new Quantity<>(fromValue, VolumeUnit.fromString(fromUnit));
                result = q.convertTo(VolumeUnit.fromString(toUnit)).getValue();
                break;
            }
            default: {
                Quantity<LengthUnit> q = new Quantity<>(fromValue, LengthUnit.fromString(fromUnit));
                result = q.convertTo(LengthUnit.fromString(toUnit)).getValue();
                break;
            }
        }

        // Round to 4 decimal places
        result = Math.round(result * 10000.0) / 10000.0;

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("CONVERT");
        entity.setOperand1(fromValue + " " + fromUnit);
        entity.setOperand2(toUnit);
        entity.setResult(String.valueOf(result));
        entity.setUserEmail(userEmail);
        repository.save(entity);

        ResponseDTO response = new ResponseDTO();
        response.setResultValue(result);
        response.setUnit(toUnit);
        return response;
    }

    @Override
    public List<ResponseDTO> getHistoryForUser(String userEmail) {
        return repository.findByUserEmail(userEmail)
                .stream()
                .map(e -> {
                    ResponseDTO dto = new ResponseDTO();
                    dto.setResultString(e.getOperand1() + " → " + e.getResult() + " " + e.getOperand2());
                    dto.setResultValue(parseDouble(e.getResult()));
                    dto.setUnit(e.getOperand2());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private double parseDouble(String val) {
        try { return Double.parseDouble(val); } catch (Exception e) { return 0.0; }
    }
}
