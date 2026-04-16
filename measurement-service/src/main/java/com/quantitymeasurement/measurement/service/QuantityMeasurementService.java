package com.quantitymeasurement.measurement.service;

import com.quantitymeasurement.measurement.client.UserServiceClient;
import com.quantitymeasurement.measurement.core.IMeasurable;
import com.quantitymeasurement.measurement.core.Quantity;
import com.quantitymeasurement.measurement.dto.*;
import com.quantitymeasurement.measurement.units.LengthUnit;
import com.quantitymeasurement.measurement.units.TemperatureUnit;
import com.quantitymeasurement.measurement.units.VolumeUnit;
import com.quantitymeasurement.measurement.units.WeightUnit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {

    private final UserServiceClient userServiceClient;

    public QuantityMeasurementService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
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
            case "WEIGHT": {
                Quantity<WeightUnit> q = new Quantity<>(fromValue, WeightUnit.fromString(fromUnit));
                result = q.convertTo(WeightUnit.fromString(toUnit)).getValue();
                break;
            }
            default: {
                Quantity<LengthUnit> q = new Quantity<>(fromValue, LengthUnit.fromString(fromUnit));
                result = q.convertTo(LengthUnit.fromString(toUnit)).getValue();
                break;
            }
        }

        result = Math.round(result * 10000.0) / 10000.0;

        // Save history via user-service (Feign call instead of direct DB save)
        if (userEmail != null) {
            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setOperation("CONVERT");
            historyDTO.setOperand1(fromValue + " " + fromUnit);
            historyDTO.setOperand2(toUnit);
            historyDTO.setResult(String.valueOf(result));
            historyDTO.setUserEmail(userEmail);
            userServiceClient.saveHistory(historyDTO);
        }

        ResponseDTO response = new ResponseDTO();
        response.setResultValue(result);
        response.setUnit(toUnit);
        return response;
    }

    @Override
    public ArithmeticResponseDTO performArithmetic(ArithmeticInputDTO dto, String userEmail) {
        String type       = dto.getType() == null ? "LENGTH" : dto.getType().toUpperCase();
        String operation  = dto.getOperation() == null ? "ADD" : dto.getOperation().toUpperCase();
        double val1       = dto.getQuantity1().getValue();
        String unit1Str   = dto.getQuantity1().getUnit();
        double val2       = dto.getQuantity2().getValue();
        String unit2Str   = dto.getQuantity2().getUnit();
        String resUnitStr = dto.getResultUnit() != null ? dto.getResultUnit() : unit1Str;

        double result;

        switch (type) {
            case "TEMPERATURE": {
                TemperatureUnit u1 = TemperatureUnit.fromString(unit1Str);
                TemperatureUnit u2 = TemperatureUnit.fromString(unit2Str);
                TemperatureUnit uR = TemperatureUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
            case "VOLUME": {
                VolumeUnit u1 = VolumeUnit.fromString(unit1Str);
                VolumeUnit u2 = VolumeUnit.fromString(unit2Str);
                VolumeUnit uR = VolumeUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
            case "WEIGHT": {
                WeightUnit u1 = WeightUnit.fromString(unit1Str);
                WeightUnit u2 = WeightUnit.fromString(unit2Str);
                WeightUnit uR = WeightUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
            default: {
                LengthUnit u1 = LengthUnit.fromString(unit1Str);
                LengthUnit u2 = LengthUnit.fromString(unit2Str);
                LengthUnit uR = LengthUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
        }

        result = Math.round(result * 10000.0) / 10000.0;

        String sym = operatorSymbol(operation);
        boolean isDivide = operation.equals("DIVIDE");
        String expression = val1 + " " + unit1Str + " " + sym + " " + val2 + " " + unit2Str
                + " = " + result + " " + (isDivide ? "(ratio)" : resUnitStr);

        // Save history via user-service (Feign call instead of direct DB save)
        if (userEmail != null) {
            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setOperation(operation);
            historyDTO.setOperand1(val1 + " " + unit1Str);
            historyDTO.setOperand2(val2 + " " + unit2Str);
            historyDTO.setResult(result + (isDivide ? "" : " " + resUnitStr));
            historyDTO.setUserEmail(userEmail);
            userServiceClient.saveHistory(historyDTO);
        }

        ArithmeticResponseDTO response = new ArithmeticResponseDTO();
        response.setResultValue(result);
        response.setResultUnit(isDivide ? "ratio" : resUnitStr);
        response.setExpression(expression);
        response.setOperation(operation);
        return response;
    }

    private <T extends IMeasurable> double computeArithmetic(
            Quantity<T> q1, Quantity<T> q2, String op, T resultUnit) {
        double base1 = q1.toBase();
        double base2 = q2.toBase();
        switch (op) {
            case "ADD":      return resultUnit.fromBaseUnit(base1 + base2);
            case "SUBTRACT": return resultUnit.fromBaseUnit(base1 - base2);
            case "MULTIPLY": return resultUnit.fromBaseUnit(base1 * base2);
            case "DIVIDE":
                if (base2 == 0) throw new ArithmeticException("Division by zero");
                return base1 / base2;
            default: throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }

    private String operatorSymbol(String op) {
        switch (op) {
            case "ADD":      return "+";
            case "SUBTRACT": return "-";
            case "MULTIPLY": return "x";
            case "DIVIDE":   return "/";
            default:         return op;
        }
    }

    @Override
    public List<ResponseDTO> getHistoryForUser(String userEmail) {
        // Fetch history from user-service via Feign (instead of direct DB query)
        return userServiceClient.getHistory(userEmail)
                .stream()
                .map(h -> {
                    ResponseDTO dto = new ResponseDTO();
                    dto.setResultString(h.getResultString());
                    dto.setResultValue(h.getResultValue());
                    dto.setUnit(h.getUnit());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
