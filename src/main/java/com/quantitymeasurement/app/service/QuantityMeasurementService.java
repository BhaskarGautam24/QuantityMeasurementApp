package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.dto.QuantityInputDTO;
import com.quantitymeasurement.app.dto.ResponseDTO;
import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.app.repository.IQuantityMeasurementRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementService(IQuantityMeasurementRepository repository) {
        this.repository = repository;
        this.repository.initializeDatabase();
    }

    @Override
    public ResponseDTO compareQuantities(QuantityInputDTO dto) {

        double value1 = dto.getThisQuantityDTO().getValue();
        double value2 = dto.getThatQuantityDTO().getValue();

        boolean result = value1 == value2;

        ResponseDTO response = new ResponseDTO();
        response.setResultString(String.valueOf(result));
        response.setResultValue(result ? 1.0 : 0.0);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("COMPARE");
        entity.setOperand1(value1 + " " + dto.getThisQuantityDTO().getUnit());
        entity.setOperand2(value2 + " " + dto.getThatQuantityDTO().getUnit());
        entity.setResult(String.valueOf(result));

        repository.save(entity);

        return response;
    }

    @Override
    public ResponseDTO convertQuantities(QuantityInputDTO dto) {

        double value = dto.getThisQuantityDTO().getValue();

        ResponseDTO response = new ResponseDTO();
        response.setResultValue(value);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("CONVERT");
        entity.setOperand1(value + " " + dto.getThisQuantityDTO().getUnit());
        entity.setOperand2("");
        entity.setResult(String.valueOf(value));

        repository.save(entity);

        return response;
    }

    @Override
    public ResponseDTO addQuantities(QuantityInputDTO dto) {

        double value1 = dto.getThisQuantityDTO().getValue();
        double value2 = dto.getThatQuantityDTO().getValue();

        double result = value1 + value2;

        ResponseDTO response = new ResponseDTO();
        response.setResultValue(result);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("ADD");
        entity.setOperand1(value1 + " " + dto.getThisQuantityDTO().getUnit());
        entity.setOperand2(value2 + " " + dto.getThatQuantityDTO().getUnit());
        entity.setResult(String.valueOf(result));

        repository.save(entity);

        return response;
    }

    @Override
    public ResponseDTO divideQuantities(QuantityInputDTO dto) {

        double value1 = dto.getThisQuantityDTO().getValue();
        double value2 = dto.getThatQuantityDTO().getValue();

        double result = value1 / value2;

        ResponseDTO response = new ResponseDTO();
        response.setResultValue(result);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("DIVIDE");
        entity.setOperand1(value1 + " " + dto.getThisQuantityDTO().getUnit());
        entity.setOperand2(value2 + " " + dto.getThatQuantityDTO().getUnit());
        entity.setResult(String.valueOf(result));

        repository.save(entity);

        return response;
    }

    @Override
    public long getOperationCount(String operation) {
        return 0;
    }

    @Override
    public List<ResponseDTO> getHistoryByOperation(String operation) {
        return new ArrayList<>();
    }

    @Override
    public List<ResponseDTO> getHistoryByType(String type) {
        return new ArrayList<>();
    }

    @Override
    public List<ResponseDTO> getErrorHistory() {
        return new ArrayList<>();
    }
}